﻿using Microsoft.AspNetCore.Mvc;
using Grpc.Net.Client;

namespace GrpcClientAPI.Controllers
{
    [ApiController]
    [Route("[controller]")]
    public class InteraccionController : Controller
    {
        public Interaccion.InteraccionClient Client { get; set; }

        public InteraccionController()
        {
            Client = new Interaccion.InteraccionClient(GrpcChannel.ForAddress("https://localhost:7072"));
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
    }
}
