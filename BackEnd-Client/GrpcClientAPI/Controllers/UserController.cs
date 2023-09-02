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
            AppContext.SetSwitch("System.Net.Http.SocketsHttpHandler.Http2UnencryptedSupport", true);
            Client = new User.UserClient(GrpcChannel.ForAddress("https://localhost:9090"));
        }

        [HttpGet()]
        [Route("[action]")]
        public async Task<getUsuarios> TraerUsuarios(Empty request)
        {
            var reply = await Client.TraerUsuariosAsync(request);

            return reply;
        }

        [HttpPost()]
        [Route("[action]")]
        public async Task<UsuarioResponse> Login(LoginUsuario request)
        {
            var reply = await Client.LoginAsync(request);

            return reply;
        }

        [HttpPost()]
        [Route("[action]")]
        public async Task<getCreado> CrearUsuario(UsuarioRequest request)
        {
            var reply = await Client.CrearUsuarioAsync(request);

            return reply;
        }

        [HttpPost()]
        [Route("[action]")]
        public async Task<getUsuarios> TraerUsuariosSeguidos(UsuarioRequestById request)
        {
            var reply = await Client.TraerUsuariosSeguidosAsync(request);

            return reply;
        }
    }
}
