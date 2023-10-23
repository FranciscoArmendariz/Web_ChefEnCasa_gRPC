using System;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.Serialization;
using System.ServiceModel;
using System.ServiceModel.Web;
using System.Text;
using System.Threading.Tasks;

namespace SOAPService
{
    // NOTE: You can use the "Rename" command on the "Refactor" menu to change the interface name "IService1" in both code and config file together.
    [ServiceContract]
    public interface IService1
    {

        [OperationContract]
        string GetData(int value);

        [OperationContract]
        void CrearMensaje(int idAutor, int idReceptor, string asunto, string mensaje, string respuesta);

        [OperationContract]
        Task<string> ResponderMensaje(int idMensaje, string respuesta);

        [OperationContract]
        Task<string> TraerMensajesPorAutor(int idAutor);

        [OperationContract]
        Task<string> TraerMensajesPorReceptor(int idReceptor);

        [OperationContract]
        Task<bool> CrearRecetario(int idAutor, string nombre);

        [OperationContract]
        Task<bool> BorrarRecetario(int idRecetario);
        
        [OperationContract]
        Task<string> TraerRecetariosPorUsuario(int idUsuario);
        
        [OperationContract]
        Task<string> TraerRecetarioConRecetas(int idRecetario);

        [OperationContract]
        Task<bool> AgregarRecetaRecetario(int idRecetario, int idReceta);
        
        [OperationContract]
        Task<bool> RemoverRecetaRecetario(int idRecetario, int idReceta);

        [OperationContract]
        CompositeType GetDataUsingDataContract(CompositeType composite);



        // TODO: Add your service operations here
    }

    public class Recetario
    {
        public int idRecetario { get; set; }
        public string nombre { get; set; }
        public List<Receta> recetas { get; set; }
    }

    public class Receta
    {
        public int IdReceta { get; set; }
        public string titulo { get; set; }
        public string descripcion { get; set; }
        public string categoria { get; set; }
        public int tiempoAprox { get; set; }
        public decimal promedio { get; set; }
        public List<FotoObj> Fotos { get; set; }
        public List<IngredienteObj> Ingredientes { get; set; }
        public List<PasoObj> Pasos { get; set; }
        public List<string> Comentarios { get; set; }
    }

    public class FotoObj
    {
        public string Url { get; set; }
    }

    public class IngredienteObj
    {
        public string Nombre { get; set; }
        public string Cantidad { get; set; }
    }

    public class PasoObj
    {
        public int Numero { get; set; }
        public string Descripcion { get; set; }
    }

    // Use a data contract as illustrated in the sample below to add composite types to service operations.
    [DataContract]
    public class CompositeType
    {
        bool boolValue = true;
        string stringValue = "Hello ";

        [DataMember]
        public bool BoolValue
        {
            get { return boolValue; }
            set { boolValue = value; }
        }

        [DataMember]
        public string StringValue
        {
            get { return stringValue; }
            set { stringValue = value; }
        }
    }
}
