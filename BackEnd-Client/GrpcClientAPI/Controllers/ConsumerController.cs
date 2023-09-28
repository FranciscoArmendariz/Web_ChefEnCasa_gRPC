using Microsoft.AspNetCore.Mvc;
using Confluent.Kafka;
using static Confluent.Kafka.ConfigPropertyNames;
using System.Threading;

namespace GrpcClientAPI.Controllers
{
    public class ConsumerController : BackgroundService
    {
        public ConsumerController()
        {
            var cConfig = new ConsumerConfig
            {
                BootstrapServers = "localhost:9092",
                SaslMechanism = SaslMechanism.Plain,
                SecurityProtocol = SecurityProtocol.SaslSsl,
                SaslUsername = "xxxxxxx",
                SaslPassword = "xxxxx+",
                GroupId = Guid.NewGuid().ToString(),
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
                    while (true)
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
                }
                catch (OperationCanceledException)
                {
                    // Close and Release all the resources held by this consumer  
                    c.Close();
                }
            }
        }

        protected override Task ExecuteAsync(CancellationToken stoppingToken)
        {
            throw new NotImplementedException();
        }
    }
}