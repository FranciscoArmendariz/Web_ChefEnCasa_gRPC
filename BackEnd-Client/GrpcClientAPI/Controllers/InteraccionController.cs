using Microsoft.AspNetCore.Mvc;
using Grpc.Net.Client;
using Confluent.Kafka;

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

            return reply;
        }

        [HttpPost()]
        [Route("[action]")]
        public async Task<StringSeguido> dejarDeSeguirUsuario(IdSeguirUsuario request)
        {
            var reply = await Client.dejarDeSeguirUsuarioAsync(request);

            return reply;
        }

        [HttpPost()]
        [Route("[action]")]
        public async Task<StringSeguido> agregarFavorito(favorito request)
        {
            var reply = await Client.agregarFavoritoAsync(request);

            return reply;
        }

        [HttpPost()]
        [Route("[action]")]
        public async Task<StringSeguido> removerFavorito(favorito request)
        {
            var reply = await Client.removerFavoritoAsync(request);

            return reply;
        }

        [HttpPost()]
        [Route("[action]")]
        public async Task<string> testConsumer()
        {
            var cConfig = new ConsumerConfig
            {
                BootstrapServers = "localhost:9092",
                GroupId = "foo",
                AutoOffsetReset = AutoOffsetReset.Earliest
            };

            using (var c = new ConsumerBuilder<Ignore, string>(cConfig).Build())
                {
                c.Subscribe("test");

                CancellationTokenSource cts = new CancellationTokenSource();
                Console.CancelKeyPress += (_, e) => {
                    e.Cancel = true; // prevent the process from terminating.
                    cts.Cancel();
                };

                try
                {
                    try
                    {
                        var cr = c.Consume(cts.Token);
                        Console.WriteLine($"Consumed message '{cr.Value}' at: '{cr.TopicPartitionOffset}'.");
                    }
                    catch (ConsumeException e)
                    {
                        Console.WriteLine($"Error occured: {e.Error.Reason}");
                    }
                }
                catch (OperationCanceledException)
                {
                    c.Close();
                }
            }

            return "";
        }

        [HttpPost()]
        [Route("[action]")]
        public async Task<string> testProducer()
        {
            var config = new ProducerConfig { BootstrapServers = "localhost:9092" };

            using var producer = new ProducerBuilder<Null, string>(config).Build();

            producer.Produce("test", new Message<Null, string> { Value = $"Hello, Kafka user! asd" });

            return "";
        }
    }
}
