using Grpc.Net.Client;
using Microsoft.AspNetCore.Mvc;
using System.Threading.Channels;

namespace GrpcClientAPI.Controllers
{
    [ApiController]
    [Route("[controller]")]
    public class RecetaController : ControllerBase
    {
        public Receta.RecetaClient Client { get; set; }

        public RecetaController()
        {
            Client = new Receta.RecetaClient(GrpcChannel.ForAddress("https://localhost:7072"));
        }

        [HttpGet()]
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
    }
}
