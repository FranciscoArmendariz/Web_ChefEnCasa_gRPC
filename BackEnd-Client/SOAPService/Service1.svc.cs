using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net.Http;
using System.Runtime.Serialization;
using System.Security.Policy;
using System.ServiceModel;
using System.ServiceModel.Web;
using System.Text;
using System.Threading.Tasks;
using System.Web.UI;

namespace SOAPService
{
    public class Service1 : IService1
    {
        private string url { set; get; } = "http://localhost:8000/api";
        private HttpClient httpClient { set; get; }
        public string GetData(int value)
        {
            return string.Format("You entered: {0}", value);
        }

        public CompositeType GetDataUsingDataContract(CompositeType composite)
        {
            if (composite == null)
            {
                throw new ArgumentNullException("composite");
            }
            if (composite.BoolValue)
            {
                composite.StringValue += "Suffix";
            }
            return composite;
        }

        public async void CrearMensaje(int idAutor, int idReceptor, string asunto, string mensaje)
        {
            try
            {
                using (var client = new HttpClient())
                {
                    var requestData = new CrearMensajeRequest
                    {
                        idAutor = idAutor,
                        idReceptor = idReceptor,
                        asunto = asunto,
                        mensaje = mensaje,
                        respuesta = null
                    };

                    var content = new StringContent(JsonConvert.SerializeObject(requestData), Encoding.UTF8, "application/json");

                    var response = await client.PostAsync(url + "/mensaje/crear", content);
                }
            }
            catch (HttpRequestException e)
            {
            }
        }

        public async Task<string> ResponderMensaje(int idMensaje, string respuesta)
        {
            var responseString = "";

            try
            {
                using (var client = new HttpClient())
                {
                    var requestData = new ResponderMensajeRequest
                    {
                        idMensaje = idMensaje,
                        respuesta = respuesta
                    };

                    var content = new StringContent(JsonConvert.SerializeObject(requestData), Encoding.UTF8, "application/json");

                    var response = await client.PostAsync(url + "/mensaje/responder", content);

                    responseString = await response.Content.ReadAsStringAsync();
                }
            }
            catch (HttpRequestException e)
            {
            }

            return responseString;
        }

        public async Task<string> TraerMensajesPorAutor(int idAutor)
        {
            var responseString = "";

            try
            {
                httpClient = new HttpClient();
                HttpResponseMessage response = await httpClient.GetAsync(url + "/mensaje/autor/" + idAutor.ToString());

                responseString = await response.Content.ReadAsStringAsync();
            }
            catch (HttpRequestException e)
            {
            }

            return responseString;

        }

        public async Task<string> TraerMensajesPorReceptor(int idReceptor)
        {
            var responseString = "";

            try
            {
                try
                {
                    httpClient = new HttpClient();
                    HttpResponseMessage response = await httpClient.GetAsync(url + "/mensaje/receptor/" + idReceptor.ToString());

                    responseString = await response.Content.ReadAsStringAsync();
                }
                catch (HttpRequestException e)
                {
                }

                return responseString;
            }
            catch (HttpRequestException e)
            {
            }

            return responseString;
        }

        public async Task<bool> CrearRecetario(int idAutor, string nombre)
        {
            try
            {
                using (var client = new HttpClient())
                {
                    var values = new Dictionary<string, string>
                    {
                        { "idAutor", idAutor.ToString() },
                        { "nombre", nombre.ToString() }
                    };

                    var content = new FormUrlEncodedContent(values);

                    var response = await client.PostAsync(url, content);
                }
            }
            catch (HttpRequestException e)
            {
            }
            return true;
        }

        public async Task<bool> BorrarRecetario(int idRecetario)
        {
            try
            {
                using (var client = new HttpClient())
                {
                    var values = new Dictionary<string, string>
                    {
                        { "idRecetario", idRecetario.ToString() }
                    };

                    var content = new FormUrlEncodedContent(values);

                    var response = await client.PostAsync(url, content);
                }
            }
            catch (HttpRequestException e)
            {
            }
            return true;
        }

        public async Task<string> TraerRecetariosPorUsuario(int idUsuario)
        {
            var responseString = "";

            try
            {
                using (var client = new HttpClient())
                {
                    var values = new Dictionary<string, string>
                    {
                        { "idUsuario", idUsuario.ToString() }
                    };

                    var content = new FormUrlEncodedContent(values);

                    var response = await client.PostAsync(url, content);

                    responseString = await response.Content.ReadAsStringAsync();
                }
            }
            catch (HttpRequestException e)
            {
            }

            return responseString;
        }

        public async Task<string> TraerRecetarioConRecetas(int idRecetario)
        {
            var responseString = "";

            try
            {
                using (var client = new HttpClient())
                {
                    var values = new Dictionary<string, string>
                    {
                        { "idRecetario", idRecetario.ToString() }
                    };

                    var content = new FormUrlEncodedContent(values);

                    var response = await client.PostAsync(url, content);

                    responseString = await response.Content.ReadAsStringAsync();
                }
            }
            catch (HttpRequestException e)
            {
            }

            return responseString;
        }

        public async Task<bool> AgregarRecetaRecetario(int idRecetario, int idReceta)
        {
            try
            {
                using (var client = new HttpClient())
                {
                    var values = new Dictionary<string, string>
                    {
                        { "idRecetario", idRecetario.ToString() },
                        { "idReceta", idReceta.ToString() }
                    };

                    var content = new FormUrlEncodedContent(values);

                    var response = await client.PostAsync(url, content);
                }
            }
            catch (HttpRequestException e)
            {
            }
            return true;
        }

        public async Task<bool> RemoverRecetaRecetario(int idRecetario, int idReceta)
        {
            try
            {
                using (var client = new HttpClient())
                {
                    var values = new Dictionary<string, string>
                    {
                        { "idRecetario", idRecetario.ToString() },
                        { "idReceta", idReceta.ToString() }
                    };

                    var content = new FormUrlEncodedContent(values);

                    var response = await client.PostAsync(url, content);
                }
            }
            catch (HttpRequestException e)
            {
            }
            return true;
        }
    }
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