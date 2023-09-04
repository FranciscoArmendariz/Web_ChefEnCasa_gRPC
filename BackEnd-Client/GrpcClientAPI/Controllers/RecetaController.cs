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
        public async Task<getRecetaCreada> CrearReceta(RecetaRequest request)
        {
            var reply = await Client.CrearRecetaAsync(request);

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
}
