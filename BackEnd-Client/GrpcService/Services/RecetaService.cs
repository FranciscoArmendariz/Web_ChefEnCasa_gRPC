using Grpc.Core;
using GrpcService;

namespace GrpcService.Services
{
    public class RecetaService : Receta.RecetaBase
    {
        public RecetaService()
        {
        }

        public override Task<GetRecetasResponse> GetRecetas(GetRecetasRequest request, ServerCallContext context)
        {
            return Task.FromResult(new GetRecetasResponse
            {
                Message = "Respuesta de GetRecetas."
            });
        }

        public override Task<AgregarRecetaResponse> AgregarReceta(AgregarRecetaRequest request, ServerCallContext context)
        {
            return Task.FromResult(new AgregarRecetaResponse
            {
                Message = true
            });
        }
        
        public override Task<EditarRecetaResponse> EditarReceta(EditarRecetaRequest request, ServerCallContext context)
        {
            return Task.FromResult(new EditarRecetaResponse
            {
                Message = true
            });
        }
        
        public override Task<TraerRecetasPorUsuarioResponse> TraerRecetasPorUsuario(TraerRecetasPorUsuarioRequest request, ServerCallContext context)
        {
            return Task.FromResult(new TraerRecetasPorUsuarioResponse
            {
                Message = "TraerRecetasPorUsuarioResponse"
            });
        }

        public override Task<FavoritoResponse> AgregarFavorito(FavoritoRequest request, ServerCallContext context)
        {
            return Task.FromResult(new FavoritoResponse
            {
                Message = true
            });
        }

        public override Task<FavoritoResponse> RemoverFavorito(FavoritoRequest request, ServerCallContext context)
        {
            return Task.FromResult(new FavoritoResponse
            {
                Message = true
            });
        }

        public override Task<TraerFavoritosResponse> TraerFavoritos(TraerFavoritosRequest request, ServerCallContext context)
        {
            return Task.FromResult(new TraerFavoritosResponse
            {
                Message = "TraerFavoritosResponse"
            });
        }

        public override Task<TraerIngredientesResponse> TraerIngredientes(TraerIngredientesRequest request, ServerCallContext context)
        {
            return Task.FromResult(new TraerIngredientesResponse
            {
                Message = "TraerIngredientesResponse"
            });
        }
    }
}
