using Confluent.Kafka;
using Google.Protobuf.Collections;
using Grpc.Net.Client;
using Microsoft.AspNetCore.Mvc;
using Newtonsoft.Json;
using System.Reflection.Metadata.Ecma335;
using System.Threading.Channels;

namespace GrpcClientAPI.Controllers
{
    [ApiController]
    public class RecetaController : ControllerBase
    {
        public receta.recetaClient Client { get; set; }

        public RecetaController()
        {
            Client = new receta.recetaClient(GrpcChannel.ForAddress("http://localhost:9090"));
        }

        [HttpPost()]
        [Route("[action]")]
        public async Task<getRecetas> TraerRecetas(RecetaRequestFilter request)
        {
            var reply = await Client.TraerRecetasAsync(request);

            return reply;
        }

        [HttpPost()]
        [Route("[action]")]
        public async Task<getRecetaCreada> CrearReceta(RecetaObj request)
        {
            RecetaRequest receta = request.Receta;

            foreach (var item in request.Ingredientes)
            {
                receta.Ingredientes.Add(new Ingrediente() { Cantidad= item.Cantidad, Nombre = item.Nombre});
            }
            foreach (var item in request.Fotos)
            {
                receta.Fotos.Add(new Foto() { Url=item.Url });
            }
            foreach (var item in request.Pasos)
            {
                receta.Pasos.Add(new Paso() { Descripcion = item.Descripcion, Numero = item.Numero });
            }
            var reply = await Client.CrearRecetaAsync(receta);

            var config = new ProducerConfig { BootstrapServers = "localhost:9092" };

            using var producer = new ProducerBuilder<Null, string>(config).Build();

            string uri = "";
            if (request.Fotos.Count() > 0) uri = request.Fotos.FirstOrDefault().Url;

            NuevaRecetaMsg msg = new NuevaRecetaMsg() { nombreDeAutor=request.nombreAutor, tituloReceta=request.Receta.Titulo, foto = uri };

            producer.Produce("novedades", new Message<Null, string> { Value = JsonConvert.SerializeObject(msg) });

            producer.Flush();
            return reply;
        }

        [HttpPost()]
        [Route("[action]")]
        public async Task<getRecetaEditada> EditarReceta(RecetaUpdateObj request)
        {
            RecetaUpdateRequest receta = request.Receta;

            foreach (var item in request.Ingredientes)
            {
                receta.Ingredientes.Add(new Ingrediente() { Cantidad = item.Cantidad, Nombre = item.Nombre });
            }
            foreach (var item in request.Fotos)
            {
                receta.Fotos.Add(new Foto() { Url = item.Url });
            }
            foreach (var item in request.Pasos)
            {
                receta.Pasos.Add(new Paso() { Descripcion = item.Descripcion, Numero = item.Numero });
            }

            var reply = await Client.EditarRecetaAsync(receta);

            return reply;
        }

        [HttpPost()]
        [Route("[action]")]
        public async Task<getRecetas> TraerRecetasPorId(UsuarioRequestByUserId request)
        {
            var reply = await Client.TraerRecetasPorIdAsync(request);

            return reply;
        }
        
        [HttpPost()]
        [Route("[action]")]
        public async Task<getRecetas> TraerRecetasFavoritas(UsuarioRequestByUserId request)
        {
            var reply = await Client.TraerRecetasFavoritasAsync(request);

            return reply;
        }

        [HttpPost()]
        [Route("[action]")]
        public async Task<ListaIngredientes> TraerIngredientes(RecetaRequestById request)
        {
            var reply = await Client.TraerIngredientesAsync(request);

            return reply;
        }

        [HttpPost()]
        [Route("[action]")]
        public async Task<RecetaResponse> TraerRecetasPorIdReceta(RecetaRequestById request)
        {
            var reply = await Client.TraerRecetasPorIdRecetaAsync(request);

            return reply;
        }

        [HttpPost()]
        [Route("[action]")]
        public async Task<ListaIngredienteSinCantidad> TraerTodosLosIngredientes(Empty2 request)
        {
            var reply = await Client.TraerTodosLosIngredientesAsync(request);

            return reply;
        }

        [HttpPost()]
        [Route("[action]")]
        public async Task<bool> NuevoComentario(ComentarioRequest request)
        {
            var config = new ProducerConfig { BootstrapServers = "localhost:9092" };

            using var producer = new ProducerBuilder<Null, string>(config).Build();

            producer.Produce("Comentarios", new Message<Null, string> { Value = JsonConvert.SerializeObject(request) });

            if (!request.esAutor)
            {
                producer.Produce("PopularidadReceta", new Message<Null, string> { Value = JsonConvert.SerializeObject(new PopularidadReceta() { idReceta = request.idReceta, calificacion = false, puntaje = 1 }) });
            }

            producer.Flush();
            return true;
        }

        [HttpPost()]
        [Route("[action]")]
        public async Task<bool> CalificarReceta(PopularidadReceta request)
        {
            var config = new ProducerConfig { BootstrapServers = "localhost:9092" };

            using var producer = new ProducerBuilder<Null, string>(config).Build();


            producer.Produce("PopularidadReceta", new Message<Null, string> { Value = JsonConvert.SerializeObject(request) });

            producer.Flush();
            return true;
        }

        [HttpPost()]
        [Route("[action]")]
        public async Task<bool> CrearRecetario(CrearRecetarioRequest request)
        {
            SOAPServiceReference.Service1Client client = new SOAPServiceReference.Service1Client();

            var rta = await client.CrearRecetarioAsync(request.idAutor, request.nombre);

            return true;
        }

        [HttpPost()]
        [Route("[action]")]
        public async Task<bool> BorrarRecetario(BorrarRecetarioRequest request)
        {
            SOAPServiceReference.Service1Client client = new SOAPServiceReference.Service1Client();

            var rta = await client.BorrarRecetarioAsync(request.idRecetario);

            return true;
        }

        [HttpPost()]
        [Route("[action]")]
        public async Task<List<Recetario>> TraerRecetariosPorUsuario(TraerRecetariosPorUsuarioRequest request)
        {
            SOAPServiceReference.Service1Client client = new SOAPServiceReference.Service1Client();

            var rta = await client.TraerRecetariosPorUsuarioAsync(request.idUsuario);

            return JsonConvert.DeserializeObject<List<Recetario>>(rta);
        }

        [HttpPost()]
        [Route("[action]")]
        public async Task<Recetario> TraerRecetarioConRecetas(TraerRecetarioConRecetasRequest request)
        {
            SOAPServiceReference.Service1Client client = new SOAPServiceReference.Service1Client();

            var rta = await client.TraerRecetarioConRecetasAsync(request.idRecetario);

            return JsonConvert.DeserializeObject<Recetario>(rta);
        }

        [HttpPost()]
        [Route("[action]")]
        public async Task<bool> AgregarRecetaRecetario(AgregarRecetaRecetarioRequest request)
        {
            SOAPServiceReference.Service1Client client = new SOAPServiceReference.Service1Client();

            var rta = await client.AgregarRecetaRecetarioAsync(request.idRecetario, request.idReceta);

            return true;
        }

        [HttpPost()]
        [Route("[action]")]
        public async Task<bool> RemoverRecetaRecetario(RemoverRecetaRecetarioRequest request)
        {
            SOAPServiceReference.Service1Client client = new SOAPServiceReference.Service1Client();

            var rta = await client.RemoverRecetaRecetarioAsync(request.idRecetario, request.idReceta);

            return true;
        }

        [HttpPost()]
        [Route("[action]")]
        public async Task<List<string>> VerUltimasRecetas()
        {
            var cConfig = new ConsumerConfig
            {
                BootstrapServers = "localhost:9092",
                GroupId = "foo",
                AutoOffsetReset = AutoOffsetReset.Earliest
            };

            List<string> recetas = new List<string>();

            using (var c = new ConsumerBuilder<Ignore, string>(cConfig).Build())
            {
                c.Subscribe("novedades");

                CancellationTokenSource cts = new CancellationTokenSource();

                Console.CancelKeyPress += (_, e) => {
                    e.Cancel = true; // prevent the process from terminating.
                    cts.Cancel();
                };


                try
                {
                    try
                    {
                        var cr = c.Consume(TimeSpan.FromSeconds(2));

                        while (cr is not null)
                        {
                            recetas.Add(cr.Value);
                            cr = c.Consume(TimeSpan.FromSeconds(2));
                        }
                    }
                    catch (ConsumeException e)
                    {
                        Console.WriteLine($"Error occured: {e.Error.Reason}");
                    }
                }
                catch (OperationCanceledException)
                {
                    c.Close();
                }
                c.Close();
            }

            return recetas;
        }
    }

    public class FotoObj
    {
        public string Url { get; set; }
    }
    
    public class ComentarioRequest
    {
        public int idRedactor { get; set; }
        public int idReceta { get; set; }
        public string comentario { get; set; }
        public bool esAutor { get; set; }
    }

    public class IngredienteObj
    {
        public string Nombre { get; set; }
        public string Cantidad { get; set; }
    }

    public class PasoObj
    {
        public int Numero { get; set; }
        public string Descripcion { get; set; }
    }

    public class RecetaObj
    {
        public RecetaRequest Receta { get; set; }
        public string nombreAutor { get; set; }
        public List<FotoObj> Fotos { get; set; }
        public List<IngredienteObj> Ingredientes { get; set; }
        public List<PasoObj> Pasos { get; set; }
    }

    public class RecetaUpdateObj
    {
        public RecetaUpdateRequest Receta { get; set; }
        public List<FotoObj> Fotos { get; set; }
        public List<IngredienteObj> Ingredientes { get; set; }
        public List<PasoObj> Pasos { get; set; }
    }

    public class NuevaRecetaMsg
    {
        public string tituloReceta { get; set; }
        public string nombreDeAutor { get; set; }
        public string foto { get; set; }
    }
    public class Recetario
    {
        public int id { get; set; }
        public int idUsuario { get; set; }
        public string nombre { get; set; }
        public List<Receta> recetas { get; set; }
    }

    public class Receta
    {
        public int id { get; set; }
        public string titulo { get; set; }
        public string descripcion { get; set; }
        public string categoria { get; set; }
        public int tiempoAprox { get; set; }
        public decimal promedio { get; set; }
        public List<FotoObj> Fotos { get; set; }
        public List<IngredienteObj> Ingredientes { get; set; }
        public List<PasoObj> Pasos { get; set; }
        public List<string> Comentarios { get; set; }
    }

    public class CrearRecetarioRequest
    {
        public string nombre { get; set; }
        public int idAutor { get; set; }
    }
    
    public class BorrarRecetarioRequest
    {
        public int idRecetario { get; set; }
    }
    
    public class TraerRecetariosPorUsuarioRequest
    {
        public int idUsuario { get; set; }
    }
    
    public class TraerRecetarioConRecetasRequest
    {
        public int idRecetario { get; set; }
    }
    
    public class AgregarRecetaRecetarioRequest
    {
        public int idRecetario { get; set; }
        public int idReceta { get; set; }
    }
    
    public class RemoverRecetaRecetarioRequest
    {
        public int idRecetario { get; set; }
        public int idReceta { get; set; }
    }
}
