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
        public async Task<string> GetRecetas()
        {
            var reply = await Client.GetRecetasAsync(new GetRecetasRequest { Name = "GetRecetasRequest" });

            return reply.Message;
        }

        [HttpPost()]
        [Route("[action]")]
        public async Task<bool> AgregarReceta(AgregarRecetaRequest request)
        {
            var reply = await Client.AgregarRecetaAsync(request);

            return reply.Message;
        }

        [HttpPost()]
        [Route("[action]")]
        public async Task<bool> EditarReceta(EditarRecetaRequest request)
        {
            var reply = await Client.EditarRecetaAsync(request);

            return reply.Message;
        }

        [HttpPost()]
        [Route("[action]")]
        public async Task<string> TraerRecetasPorUsuario(TraerRecetasPorUsuarioRequest request)
        {
            var reply = await Client.TraerRecetasPorUsuarioAsync(request);

            return reply.Message;
        }

        [HttpPost()]
        [Route("[action]")]
        public async Task<bool> AgregarFavorito(FavoritoRequest request)
        {
            var reply = await Client.AgregarFavoritoAsync(request);

            return reply.Message;
        }

        [HttpPost()]
        [Route("[action]")]
        public async Task<bool> RemoverFavorito(FavoritoRequest request)
        {
            var reply = await Client.RemoverFavoritoAsync(request);

            return reply.Message;
        }
        
        [HttpPost()]
        [Route("[action]")]
        public async Task<string> TraerFavoritos(TraerFavoritosRequest request)
        {
            var reply = await Client.TraerFavoritosAsync(request);

            return reply.Message;
        }

        [HttpPost()]
        [Route("[action]")]
        public async Task<string> TraerIngredientes(TraerIngredientesRequest request)
        {
            var reply = await Client.TraerIngredientesAsync(request);

            return reply.Message;
        }
    }
}
