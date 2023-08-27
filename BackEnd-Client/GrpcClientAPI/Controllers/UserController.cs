using Grpc.Net.Client;
using Microsoft.AspNetCore.Mvc;

namespace GrpcClientAPI.Controllers
{
    [ApiController]
    [Route("[controller]")]
    public class UserController : ControllerBase
    {
        public User.UserClient Client { get; set; }

        public UserController()
        {
            Client = new User.UserClient(GrpcChannel.ForAddress("https://localhost:7072"));
        }

        [HttpGet()]
        [Route("[action]")]
        public async Task<string> TraerUsuarios()
        {
            var reply = await Client.TraerUsuariosAsync(new TraerUsuariosRequest { Name = "TraerUsuariosRequest" });

            return reply.Message;
        }

        [HttpPost()]
        [Route("[action]")]
        public async Task<bool> IniciarSesion(IniciarSesionRequest request)
        {
            var reply = await Client.IniciarSesionAsync(request);

            return reply.Message;
        }

        [HttpPost()]
        [Route("[action]")]
        public async Task<bool> AgregarUsuario(AgregarUsuarioRequest request)
        {
            var reply = await Client.AgregarUsuarioAsync(request);

            return reply.Message;
        }

        [HttpPost()]
        [Route("[action]")]
        public async Task<bool> SeguirUsuario(SeguirUsuarioRequest request)
        {
            var reply = await Client.SeguirUsuarioAsync(request);

            return reply.Message;
        }

        [HttpPost()]
        [Route("[action]")]
        public async Task<bool> DejarDeSeguirUsuario(DejarDeSeguirUsuarioRequest request)
        {
            var reply = await Client.DejarDeSeguirUsuarioAsync(request);

            return reply.Message;
        }

        [HttpPost()]
        [Route("[action]")]
        public async Task<string> TraerUsuariosSeguidos(TraerUsuariosSeguidosRequest request)
        {
            var reply = await Client.TraerUsuariosSeguidosAsync(request);

            return reply.Message;
        }
    }
}
