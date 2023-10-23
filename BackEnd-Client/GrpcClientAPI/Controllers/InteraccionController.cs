using Microsoft.AspNetCore.Mvc;
using Grpc.Net.Client;
using Confluent.Kafka;
using Newtonsoft.Json;

namespace GrpcClientAPI.Controllers
{
    [ApiController]
    [Route("[controller]")]
    public class InteraccionController : Controller
    {
        public interaccion.interaccionClient Client { get; set; }

        public InteraccionController()
        {
           
            Client = new interaccion.interaccionClient(GrpcChannel.ForAddress("http://localhost:9090"));
        }

        [HttpPost()]
        [Route("[action]")]
        public async Task<StringSeguido> seguirUsuario(IdSeguirUsuario request)
        {
            var reply = await Client.seguirUsuarioAsync(request);

            var config = new ProducerConfig { BootstrapServers = "localhost:9092" };

            using var producer = new ProducerBuilder<Null, string>(config).Build();

            PopularidadMsg msg = new PopularidadMsg() { idUsuarioSeguido = request.IdSeguir, puntaje = 1 };

            producer.Produce("Popularidadusuario", new Message<Null, string> { Value = JsonConvert.SerializeObject(msg) });

            producer.Flush();
            return reply;
        }

        [HttpPost()]
        [Route("[action]")]
        public async Task<StringSeguido> dejarDeSeguirUsuario(IdSeguirUsuario request)
        {
            var reply = await Client.dejarDeSeguirUsuarioAsync(request);

            var config = new ProducerConfig { BootstrapServers = "localhost:9092" };

            using var producer = new ProducerBuilder<Null, string>(config).Build();

            PopularidadMsg msg = new PopularidadMsg() { idUsuarioSeguido = request.IdSeguir, puntaje = -1 };

            producer.Produce("Popularidadusuario", new Message<Null, string> { Value = JsonConvert.SerializeObject(msg) });

            producer.Flush();
            return reply;
        }

        [HttpPost()]
        [Route("[action]")]
        public async Task<StringSeguido> agregarFavorito(favorito request)
        {
            var reply = await Client.agregarFavoritoAsync(request);

            var config = new ProducerConfig { BootstrapServers = "localhost:9092" };

            using var producer = new ProducerBuilder<Null, string>(config).Build();

            producer.Produce("PopularidadReceta", new Message<Null, string> { Value = JsonConvert.SerializeObject(new PopularidadReceta() { idReceta = request.IdReceta, puntaje = 1, calificacion = false}) });

            producer.Flush();
            return reply;
        }

        [HttpPost()]
        [Route("[action]")]
        public async Task<StringSeguido> removerFavorito(favorito request)
        {
            var reply = await Client.removerFavoritoAsync(request);

            var config = new ProducerConfig { BootstrapServers = "localhost:9092" };

            using var producer = new ProducerBuilder<Null, string>(config).Build();

            producer.Produce("PopularidadReceta", new Message<Null, string> { Value = JsonConvert.SerializeObject(new PopularidadReceta() { idReceta = request.IdReceta, puntaje = -1, calificacion = false }) });

            producer.Flush();
            return reply;
        }

        [HttpPost()]
        [Route("[action]")]
        public async Task<bool> CrearMensaje(CrearMensajeRequest request)
        {
            SOAPServiceReference.Service1Client client = new SOAPServiceReference.Service1Client();

            await client.CrearMensajeAsync(request.idAutor, request.idReceptor, request.asunto, request.mensaje, request.respuesta);

            return true;
        }

        [HttpPost()]
        [Route("[action]")]
        public async Task<bool> ResponderMensaje(ResponderMensajeRequest request)
        {
            SOAPServiceReference.Service1Client client = new SOAPServiceReference.Service1Client();

            string rta = await client.ResponderMensajeAsync(request.idMensaje, request.respuesta);
            
            return true;
        }

        [HttpPost()]
        [Route("[action]")]
        public async Task<List<TraerMensajesPorAutorResponse>> TraerMensajesPorAutor(TraerMensajesPorAutorRequest request) 
        {
            SOAPServiceReference.Service1Client client = new SOAPServiceReference.Service1Client();

            var rta = await client.TraerMensajesPorAutorAsync(request.idAutor);

            return JsonConvert.DeserializeObject<List<TraerMensajesPorAutorResponse>>(rta);
        }

        [HttpPost()]
        [Route("[action]")]
        public async Task<List<TraerMensajesPorReceptorResponse>> TraerMensajesPorReceptor(TraerMensajesPorReceptorRequest request) 
        {
            SOAPServiceReference.Service1Client client = new SOAPServiceReference.Service1Client();

            string rta = await client.TraerMensajesPorReceptorAsync(request.idReceptor);

            return JsonConvert.DeserializeObject<List<TraerMensajesPorReceptorResponse>>(rta);
        }
    }

    public class PopularidadMsg
    {
        public long idUsuarioSeguido { get; set; }
        public int puntaje { get; set; }
    }

    public class PopularidadReceta
    {
        public long idReceta { get; set; }
        public int puntaje { get; set; }
        public bool calificacion { get; set; }
    }
    public class CrearMensajeRequest
    {
        public int idAutor { set; get; }
        public int idReceptor { set; get; }
        public string asunto { set; get; }
        public string mensaje { set; get; }
        public string respuesta { set; get; }
    }

    public class ResponderMensajeRequest
    {
        public int idMensaje { set; get; }
        public string respuesta { set; get; }
    }

    public class TraerMensajesPorAutorRequest
    {
        public int idAutor { set; get; }
    }

    public class TraerMensajesPorReceptorRequest
    {
        public int idReceptor { set; get; }
    }

    public class TraerMensajesPorAutorResponse
    {
        public int idMensaje { set; get; }
        public string nombreAutor { set; get; }
        public string nombreReceptor { set; get; }
        public string asunto { set; get; }
        public string mensaje { set; get; }
        public string respuesta { set; get; }
    }
    public class TraerMensajesPorReceptorResponse
    {
        public int idMensaje { set; get; }
        public string nombreAutor { set; get; }
        public string nombreReceptor { set; get; }
        public string asunto { set; get; }
        public string mensaje { set; get; }
        public string respuesta { set; get; }
    }
}
