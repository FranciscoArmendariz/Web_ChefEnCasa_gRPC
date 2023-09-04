using Google.Protobuf.Collections;
using Grpc.Net.Client;
using Microsoft.AspNetCore.Mvc;
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
        public  void Test(Test request)
        {
            RecetaRequest receta = new RecetaRequest();
            //receta.Fotos.Add(request.Fotos);
            //foreach (var foto in request.Fotos)
            //{

            //}
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

            return reply;
        }

        [HttpPost()]
        [Route("[action]")]
        public async Task<getRecetaEditada> EditarReceta(RecetaUpdateRequest request)
        {
            var reply = await Client.EditarRecetaAsync(request);

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
    }

    public class Test
    {
        public List<FotoObj> Fotos { get; set; }
    }

    public class FotoObj
    {
        public string Url { get; set; }
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
        public List<FotoObj> Fotos { get; set; }
        public List<IngredienteObj> Ingredientes { get; set; }
        public List<PasoObj> Pasos { get; set; }
    }
}
