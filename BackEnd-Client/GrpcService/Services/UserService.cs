using Grpc.Core;
using GrpcService;

namespace GrpcService.Services
{
    public class UserService : User.UserBase
    {
        public UserService()
        {
        }

        public override Task<TraerUsuariosResponse> TraerUsuarios(TraerUsuariosRequest request, ServerCallContext context)
        {
            return Task.FromResult(new TraerUsuariosResponse
            {
                Message = "Respuesta de TraerUsuarios."
            });
        }

        public override Task<IniciarSesionResponse> IniciarSesion(IniciarSesionRequest request, ServerCallContext context)
        {
            return Task.FromResult(new IniciarSesionResponse
            {
                Message = true
            });
        }
        
        public override Task<AgregarUsuarioResponse> AgregarUsuario(AgregarUsuarioRequest request, ServerCallContext context)
        {
            return Task.FromResult(new AgregarUsuarioResponse
            {
                Message = true
            });
        }

        public override Task<SeguirUsuarioResponse> SeguirUsuario(SeguirUsuarioRequest request, ServerCallContext context)
        {
            return Task.FromResult(new SeguirUsuarioResponse
            {
                Message = true
            });
        }

        public override Task<DejarDeSeguirUsuarioResponse> DejarDeSeguirUsuario(DejarDeSeguirUsuarioRequest request, ServerCallContext context)
        {
            return Task.FromResult(new DejarDeSeguirUsuarioResponse
            {
                Message = true
            });
        }

        public override Task<TraerUsuariosSeguidosResponse> TraerUsuariosSeguidos(TraerUsuariosSeguidosRequest request, ServerCallContext context)
        {
            return Task.FromResult(new TraerUsuariosSeguidosResponse
            {
                Message = "TraerUsuariosSeguidosResponse"
            });
        }
    }
}
