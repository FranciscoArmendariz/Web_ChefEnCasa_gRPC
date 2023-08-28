using Grpc.Net.Client;
using System;

namespace GrpcClient // Note: actual namespace depends on the project name.
{
    internal class Program
    {
        static async Task Main(string[] args)
        {
            Console.WriteLine("press key to continue");
            Console.ReadLine();
            using var channel = GrpcChannel.ForAddress("https://localhost:7072");
            var client = new Greeter.GreeterClient(channel);
            var reply = await client.SayHelloAsync(new HelloRequest { Name = "Mensaje enviado desde GrpcClient"});
            Console.WriteLine($"Mensaje: {reply.Message}");
            Console.WriteLine($"press any key to exit");
            Console.ReadLine();
        }
    }
}