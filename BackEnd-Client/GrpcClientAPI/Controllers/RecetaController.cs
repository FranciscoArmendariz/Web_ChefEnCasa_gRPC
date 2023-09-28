using Confluent.Kafka;
using Google.Protobuf.Collections;
using Grpc.Net.Client;
using Microsoft.AspNetCore.Mvc;
using Newtonsoft.Json;
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
        public async Task<bool> NuevoComentario(Comentario request)
        {
            var config = new ProducerConfig { BootstrapServers = "localhost:9092" };

            using var producer = new ProducerBuilder<Null, string>(config).Build();

            producer.Produce("Comentarios", new Message<Null, string> { Value = JsonConvert.SerializeObject(request) });

            if (!request.esAutor)
            {
                producer.Produce("PopularidadReceta", new Message<Null, string> { Value = JsonConvert.SerializeObject(new PopularidadReceta() { idReceta=request.idReceta, calificacion = false, puntaje = 1}) });
            }

            return true;
        }

        [HttpPost()]
        [Route("[action]")]
        public async Task<bool> CalificarReceta(PopularidadReceta request)
        {
            var config = new ProducerConfig { BootstrapServers = "localhost:9092" };

            using var producer = new ProducerBuilder<Null, string>(config).Build();

            producer.Produce("PopularidadReceta", new Message<Null, string> { Value = JsonConvert.SerializeObject(request) });

            return true;
        }

        [HttpPost()]
        [Route("[action]")]
        public async Task<bool> AgregarFavorito(PopularidadReceta request)
        {
            var config = new ProducerConfig { BootstrapServers = "localhost:9092" };

            using var producer = new ProducerBuilder<Null, string>(config).Build();

            producer.Produce("PopularidadReceta", new Message<Null, string> { Value = JsonConvert.SerializeObject(request) });

            return true;
        }

        [HttpPost()]
        [Route("[action]")]
        public async Task<bool> RemoverFavorito(PopularidadReceta request)
        {
            var config = new ProducerConfig { BootstrapServers = "localhost:9092" };

            using var producer = new ProducerBuilder<Null, string>(config).Build();

            producer.Produce("PopularidadReceta", new Message<Null, string> { Value = JsonConvert.SerializeObject(request) });

            return true;
        }
    }

    public class FotoObj
    {
        public string Url { get; set; }
    }
    
    public class PopularidadReceta
    {
        public int idReceta { get; set; }
        public int puntaje { get; set; }
        public bool calificacion { get; set; }
    }

    public class Comentario
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
}
