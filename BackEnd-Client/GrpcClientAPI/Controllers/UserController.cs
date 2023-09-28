using Confluent.Kafka;
using Grpc.Net.Client;
using Microsoft.AspNetCore.Mvc;
using Newtonsoft.Json;

namespace GrpcClientAPI.Controllers
{
    [ApiController]
    [Route("[controller]")]
    public class UserController : ControllerBase
    {
        public usuario.usuarioClient Client { get; set; }

        public UserController()
        {
            Client = new usuario.usuarioClient(GrpcChannel.ForAddress("http://localhost:9090"));
        }

        [HttpPost()]
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

        [HttpPost()]
        [Route("[action]")]
        public async Task<PopularidadMsg> SeguirUsuario(SeguirUsuarioRequest request)
        {
            var config = new ProducerConfig { BootstrapServers = "localhost:9092" };

            using var producer = new ProducerBuilder<Null, string>(config).Build();

            PopularidadMsg msg = new PopularidadMsg() { idUsuarioSeguido = request.idUsuarioSeguido, puntaje = 1};

            producer.Produce("Popularidadusuario", new Message<Null, string> { Value = JsonConvert.SerializeObject(msg)});

            return msg;
        }

        [HttpPost()]
        [Route("[action]")]
        public async Task<PopularidadMsg> DejarDeSeguirUsuario(SeguirUsuarioRequest request)
        {
            var config = new ProducerConfig { BootstrapServers = "localhost:9092" };

            using var producer = new ProducerBuilder<Null, string>(config).Build();

            PopularidadMsg msg = new PopularidadMsg() { idUsuarioSeguido = request.idUsuarioSeguido, puntaje = -1 };

            producer.Produce("Popularidadusuario", new Message<Null, string> { Value = JsonConvert.SerializeObject(msg) });

            return msg;
        }
    }

    public class SeguirUsuarioRequest
    {
        public int idUsuarioSeguido { get; set; }
    }

    public class PopularidadMsg
    {
        public int idUsuarioSeguido { get; set; }
        public int puntaje { get; set; }
    }
}
