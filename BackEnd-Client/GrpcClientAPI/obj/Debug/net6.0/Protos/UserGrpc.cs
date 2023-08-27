// <auto-generated>
//     Generated by the protocol buffer compiler.  DO NOT EDIT!
//     source: Protos/user.proto
// </auto-generated>
#pragma warning disable 0414, 1591, 8981, 0612
#region Designer generated code

using grpc = global::Grpc.Core;

namespace GrpcClientAPI {
  public static partial class User
  {
    static readonly string __ServiceName = "user.User";

    [global::System.CodeDom.Compiler.GeneratedCode("grpc_csharp_plugin", null)]
    static void __Helper_SerializeMessage(global::Google.Protobuf.IMessage message, grpc::SerializationContext context)
    {
      #if !GRPC_DISABLE_PROTOBUF_BUFFER_SERIALIZATION
      if (message is global::Google.Protobuf.IBufferMessage)
      {
        context.SetPayloadLength(message.CalculateSize());
        global::Google.Protobuf.MessageExtensions.WriteTo(message, context.GetBufferWriter());
        context.Complete();
        return;
      }
      #endif
      context.Complete(global::Google.Protobuf.MessageExtensions.ToByteArray(message));
    }

    [global::System.CodeDom.Compiler.GeneratedCode("grpc_csharp_plugin", null)]
    static class __Helper_MessageCache<T>
    {
      public static readonly bool IsBufferMessage = global::System.Reflection.IntrospectionExtensions.GetTypeInfo(typeof(global::Google.Protobuf.IBufferMessage)).IsAssignableFrom(typeof(T));
    }

    [global::System.CodeDom.Compiler.GeneratedCode("grpc_csharp_plugin", null)]
    static T __Helper_DeserializeMessage<T>(grpc::DeserializationContext context, global::Google.Protobuf.MessageParser<T> parser) where T : global::Google.Protobuf.IMessage<T>
    {
      #if !GRPC_DISABLE_PROTOBUF_BUFFER_SERIALIZATION
      if (__Helper_MessageCache<T>.IsBufferMessage)
      {
        return parser.ParseFrom(context.PayloadAsReadOnlySequence());
      }
      #endif
      return parser.ParseFrom(context.PayloadAsNewBuffer());
    }

    [global::System.CodeDom.Compiler.GeneratedCode("grpc_csharp_plugin", null)]
    static readonly grpc::Marshaller<global::GrpcClientAPI.TraerUsuariosRequest> __Marshaller_user_TraerUsuariosRequest = grpc::Marshallers.Create(__Helper_SerializeMessage, context => __Helper_DeserializeMessage(context, global::GrpcClientAPI.TraerUsuariosRequest.Parser));
    [global::System.CodeDom.Compiler.GeneratedCode("grpc_csharp_plugin", null)]
    static readonly grpc::Marshaller<global::GrpcClientAPI.TraerUsuariosResponse> __Marshaller_user_TraerUsuariosResponse = grpc::Marshallers.Create(__Helper_SerializeMessage, context => __Helper_DeserializeMessage(context, global::GrpcClientAPI.TraerUsuariosResponse.Parser));
    [global::System.CodeDom.Compiler.GeneratedCode("grpc_csharp_plugin", null)]
    static readonly grpc::Marshaller<global::GrpcClientAPI.IniciarSesionRequest> __Marshaller_user_IniciarSesionRequest = grpc::Marshallers.Create(__Helper_SerializeMessage, context => __Helper_DeserializeMessage(context, global::GrpcClientAPI.IniciarSesionRequest.Parser));
    [global::System.CodeDom.Compiler.GeneratedCode("grpc_csharp_plugin", null)]
    static readonly grpc::Marshaller<global::GrpcClientAPI.IniciarSesionResponse> __Marshaller_user_IniciarSesionResponse = grpc::Marshallers.Create(__Helper_SerializeMessage, context => __Helper_DeserializeMessage(context, global::GrpcClientAPI.IniciarSesionResponse.Parser));
    [global::System.CodeDom.Compiler.GeneratedCode("grpc_csharp_plugin", null)]
    static readonly grpc::Marshaller<global::GrpcClientAPI.AgregarUsuarioRequest> __Marshaller_user_AgregarUsuarioRequest = grpc::Marshallers.Create(__Helper_SerializeMessage, context => __Helper_DeserializeMessage(context, global::GrpcClientAPI.AgregarUsuarioRequest.Parser));
    [global::System.CodeDom.Compiler.GeneratedCode("grpc_csharp_plugin", null)]
    static readonly grpc::Marshaller<global::GrpcClientAPI.AgregarUsuarioResponse> __Marshaller_user_AgregarUsuarioResponse = grpc::Marshallers.Create(__Helper_SerializeMessage, context => __Helper_DeserializeMessage(context, global::GrpcClientAPI.AgregarUsuarioResponse.Parser));
    [global::System.CodeDom.Compiler.GeneratedCode("grpc_csharp_plugin", null)]
    static readonly grpc::Marshaller<global::GrpcClientAPI.SeguirUsuarioRequest> __Marshaller_user_SeguirUsuarioRequest = grpc::Marshallers.Create(__Helper_SerializeMessage, context => __Helper_DeserializeMessage(context, global::GrpcClientAPI.SeguirUsuarioRequest.Parser));
    [global::System.CodeDom.Compiler.GeneratedCode("grpc_csharp_plugin", null)]
    static readonly grpc::Marshaller<global::GrpcClientAPI.SeguirUsuarioResponse> __Marshaller_user_SeguirUsuarioResponse = grpc::Marshallers.Create(__Helper_SerializeMessage, context => __Helper_DeserializeMessage(context, global::GrpcClientAPI.SeguirUsuarioResponse.Parser));
    [global::System.CodeDom.Compiler.GeneratedCode("grpc_csharp_plugin", null)]
    static readonly grpc::Marshaller<global::GrpcClientAPI.DejarDeSeguirUsuarioRequest> __Marshaller_user_DejarDeSeguirUsuarioRequest = grpc::Marshallers.Create(__Helper_SerializeMessage, context => __Helper_DeserializeMessage(context, global::GrpcClientAPI.DejarDeSeguirUsuarioRequest.Parser));
    [global::System.CodeDom.Compiler.GeneratedCode("grpc_csharp_plugin", null)]
    static readonly grpc::Marshaller<global::GrpcClientAPI.DejarDeSeguirUsuarioResponse> __Marshaller_user_DejarDeSeguirUsuarioResponse = grpc::Marshallers.Create(__Helper_SerializeMessage, context => __Helper_DeserializeMessage(context, global::GrpcClientAPI.DejarDeSeguirUsuarioResponse.Parser));
    [global::System.CodeDom.Compiler.GeneratedCode("grpc_csharp_plugin", null)]
    static readonly grpc::Marshaller<global::GrpcClientAPI.TraerUsuariosSeguidosRequest> __Marshaller_user_TraerUsuariosSeguidosRequest = grpc::Marshallers.Create(__Helper_SerializeMessage, context => __Helper_DeserializeMessage(context, global::GrpcClientAPI.TraerUsuariosSeguidosRequest.Parser));
    [global::System.CodeDom.Compiler.GeneratedCode("grpc_csharp_plugin", null)]
    static readonly grpc::Marshaller<global::GrpcClientAPI.TraerUsuariosSeguidosResponse> __Marshaller_user_TraerUsuariosSeguidosResponse = grpc::Marshallers.Create(__Helper_SerializeMessage, context => __Helper_DeserializeMessage(context, global::GrpcClientAPI.TraerUsuariosSeguidosResponse.Parser));

    [global::System.CodeDom.Compiler.GeneratedCode("grpc_csharp_plugin", null)]
    static readonly grpc::Method<global::GrpcClientAPI.TraerUsuariosRequest, global::GrpcClientAPI.TraerUsuariosResponse> __Method_TraerUsuarios = new grpc::Method<global::GrpcClientAPI.TraerUsuariosRequest, global::GrpcClientAPI.TraerUsuariosResponse>(
        grpc::MethodType.Unary,
        __ServiceName,
        "TraerUsuarios",
        __Marshaller_user_TraerUsuariosRequest,
        __Marshaller_user_TraerUsuariosResponse);

    [global::System.CodeDom.Compiler.GeneratedCode("grpc_csharp_plugin", null)]
    static readonly grpc::Method<global::GrpcClientAPI.IniciarSesionRequest, global::GrpcClientAPI.IniciarSesionResponse> __Method_IniciarSesion = new grpc::Method<global::GrpcClientAPI.IniciarSesionRequest, global::GrpcClientAPI.IniciarSesionResponse>(
        grpc::MethodType.Unary,
        __ServiceName,
        "IniciarSesion",
        __Marshaller_user_IniciarSesionRequest,
        __Marshaller_user_IniciarSesionResponse);

    [global::System.CodeDom.Compiler.GeneratedCode("grpc_csharp_plugin", null)]
    static readonly grpc::Method<global::GrpcClientAPI.AgregarUsuarioRequest, global::GrpcClientAPI.AgregarUsuarioResponse> __Method_AgregarUsuario = new grpc::Method<global::GrpcClientAPI.AgregarUsuarioRequest, global::GrpcClientAPI.AgregarUsuarioResponse>(
        grpc::MethodType.Unary,
        __ServiceName,
        "AgregarUsuario",
        __Marshaller_user_AgregarUsuarioRequest,
        __Marshaller_user_AgregarUsuarioResponse);

    [global::System.CodeDom.Compiler.GeneratedCode("grpc_csharp_plugin", null)]
    static readonly grpc::Method<global::GrpcClientAPI.SeguirUsuarioRequest, global::GrpcClientAPI.SeguirUsuarioResponse> __Method_SeguirUsuario = new grpc::Method<global::GrpcClientAPI.SeguirUsuarioRequest, global::GrpcClientAPI.SeguirUsuarioResponse>(
        grpc::MethodType.Unary,
        __ServiceName,
        "SeguirUsuario",
        __Marshaller_user_SeguirUsuarioRequest,
        __Marshaller_user_SeguirUsuarioResponse);

    [global::System.CodeDom.Compiler.GeneratedCode("grpc_csharp_plugin", null)]
    static readonly grpc::Method<global::GrpcClientAPI.DejarDeSeguirUsuarioRequest, global::GrpcClientAPI.DejarDeSeguirUsuarioResponse> __Method_DejarDeSeguirUsuario = new grpc::Method<global::GrpcClientAPI.DejarDeSeguirUsuarioRequest, global::GrpcClientAPI.DejarDeSeguirUsuarioResponse>(
        grpc::MethodType.Unary,
        __ServiceName,
        "DejarDeSeguirUsuario",
        __Marshaller_user_DejarDeSeguirUsuarioRequest,
        __Marshaller_user_DejarDeSeguirUsuarioResponse);

    [global::System.CodeDom.Compiler.GeneratedCode("grpc_csharp_plugin", null)]
    static readonly grpc::Method<global::GrpcClientAPI.TraerUsuariosSeguidosRequest, global::GrpcClientAPI.TraerUsuariosSeguidosResponse> __Method_TraerUsuariosSeguidos = new grpc::Method<global::GrpcClientAPI.TraerUsuariosSeguidosRequest, global::GrpcClientAPI.TraerUsuariosSeguidosResponse>(
        grpc::MethodType.Unary,
        __ServiceName,
        "TraerUsuariosSeguidos",
        __Marshaller_user_TraerUsuariosSeguidosRequest,
        __Marshaller_user_TraerUsuariosSeguidosResponse);

    /// <summary>Service descriptor</summary>
    public static global::Google.Protobuf.Reflection.ServiceDescriptor Descriptor
    {
      get { return global::GrpcClientAPI.UserReflection.Descriptor.Services[0]; }
    }

    /// <summary>Client for User</summary>
    public partial class UserClient : grpc::ClientBase<UserClient>
    {
      /// <summary>Creates a new client for User</summary>
      /// <param name="channel">The channel to use to make remote calls.</param>
      [global::System.CodeDom.Compiler.GeneratedCode("grpc_csharp_plugin", null)]
      public UserClient(grpc::ChannelBase channel) : base(channel)
      {
      }
      /// <summary>Creates a new client for User that uses a custom <c>CallInvoker</c>.</summary>
      /// <param name="callInvoker">The callInvoker to use to make remote calls.</param>
      [global::System.CodeDom.Compiler.GeneratedCode("grpc_csharp_plugin", null)]
      public UserClient(grpc::CallInvoker callInvoker) : base(callInvoker)
      {
      }
      /// <summary>Protected parameterless constructor to allow creation of test doubles.</summary>
      [global::System.CodeDom.Compiler.GeneratedCode("grpc_csharp_plugin", null)]
      protected UserClient() : base()
      {
      }
      /// <summary>Protected constructor to allow creation of configured clients.</summary>
      /// <param name="configuration">The client configuration.</param>
      [global::System.CodeDom.Compiler.GeneratedCode("grpc_csharp_plugin", null)]
      protected UserClient(ClientBaseConfiguration configuration) : base(configuration)
      {
      }

      [global::System.CodeDom.Compiler.GeneratedCode("grpc_csharp_plugin", null)]
      public virtual global::GrpcClientAPI.TraerUsuariosResponse TraerUsuarios(global::GrpcClientAPI.TraerUsuariosRequest request, grpc::Metadata headers = null, global::System.DateTime? deadline = null, global::System.Threading.CancellationToken cancellationToken = default(global::System.Threading.CancellationToken))
      {
        return TraerUsuarios(request, new grpc::CallOptions(headers, deadline, cancellationToken));
      }
      [global::System.CodeDom.Compiler.GeneratedCode("grpc_csharp_plugin", null)]
      public virtual global::GrpcClientAPI.TraerUsuariosResponse TraerUsuarios(global::GrpcClientAPI.TraerUsuariosRequest request, grpc::CallOptions options)
      {
        return CallInvoker.BlockingUnaryCall(__Method_TraerUsuarios, null, options, request);
      }
      [global::System.CodeDom.Compiler.GeneratedCode("grpc_csharp_plugin", null)]
      public virtual grpc::AsyncUnaryCall<global::GrpcClientAPI.TraerUsuariosResponse> TraerUsuariosAsync(global::GrpcClientAPI.TraerUsuariosRequest request, grpc::Metadata headers = null, global::System.DateTime? deadline = null, global::System.Threading.CancellationToken cancellationToken = default(global::System.Threading.CancellationToken))
      {
        return TraerUsuariosAsync(request, new grpc::CallOptions(headers, deadline, cancellationToken));
      }
      [global::System.CodeDom.Compiler.GeneratedCode("grpc_csharp_plugin", null)]
      public virtual grpc::AsyncUnaryCall<global::GrpcClientAPI.TraerUsuariosResponse> TraerUsuariosAsync(global::GrpcClientAPI.TraerUsuariosRequest request, grpc::CallOptions options)
      {
        return CallInvoker.AsyncUnaryCall(__Method_TraerUsuarios, null, options, request);
      }
      [global::System.CodeDom.Compiler.GeneratedCode("grpc_csharp_plugin", null)]
      public virtual global::GrpcClientAPI.IniciarSesionResponse IniciarSesion(global::GrpcClientAPI.IniciarSesionRequest request, grpc::Metadata headers = null, global::System.DateTime? deadline = null, global::System.Threading.CancellationToken cancellationToken = default(global::System.Threading.CancellationToken))
      {
        return IniciarSesion(request, new grpc::CallOptions(headers, deadline, cancellationToken));
      }
      [global::System.CodeDom.Compiler.GeneratedCode("grpc_csharp_plugin", null)]
      public virtual global::GrpcClientAPI.IniciarSesionResponse IniciarSesion(global::GrpcClientAPI.IniciarSesionRequest request, grpc::CallOptions options)
      {
        return CallInvoker.BlockingUnaryCall(__Method_IniciarSesion, null, options, request);
      }
      [global::System.CodeDom.Compiler.GeneratedCode("grpc_csharp_plugin", null)]
      public virtual grpc::AsyncUnaryCall<global::GrpcClientAPI.IniciarSesionResponse> IniciarSesionAsync(global::GrpcClientAPI.IniciarSesionRequest request, grpc::Metadata headers = null, global::System.DateTime? deadline = null, global::System.Threading.CancellationToken cancellationToken = default(global::System.Threading.CancellationToken))
      {
        return IniciarSesionAsync(request, new grpc::CallOptions(headers, deadline, cancellationToken));
      }
      [global::System.CodeDom.Compiler.GeneratedCode("grpc_csharp_plugin", null)]
      public virtual grpc::AsyncUnaryCall<global::GrpcClientAPI.IniciarSesionResponse> IniciarSesionAsync(global::GrpcClientAPI.IniciarSesionRequest request, grpc::CallOptions options)
      {
        return CallInvoker.AsyncUnaryCall(__Method_IniciarSesion, null, options, request);
      }
      [global::System.CodeDom.Compiler.GeneratedCode("grpc_csharp_plugin", null)]
      public virtual global::GrpcClientAPI.AgregarUsuarioResponse AgregarUsuario(global::GrpcClientAPI.AgregarUsuarioRequest request, grpc::Metadata headers = null, global::System.DateTime? deadline = null, global::System.Threading.CancellationToken cancellationToken = default(global::System.Threading.CancellationToken))
      {
        return AgregarUsuario(request, new grpc::CallOptions(headers, deadline, cancellationToken));
      }
      [global::System.CodeDom.Compiler.GeneratedCode("grpc_csharp_plugin", null)]
      public virtual global::GrpcClientAPI.AgregarUsuarioResponse AgregarUsuario(global::GrpcClientAPI.AgregarUsuarioRequest request, grpc::CallOptions options)
      {
        return CallInvoker.BlockingUnaryCall(__Method_AgregarUsuario, null, options, request);
      }
      [global::System.CodeDom.Compiler.GeneratedCode("grpc_csharp_plugin", null)]
      public virtual grpc::AsyncUnaryCall<global::GrpcClientAPI.AgregarUsuarioResponse> AgregarUsuarioAsync(global::GrpcClientAPI.AgregarUsuarioRequest request, grpc::Metadata headers = null, global::System.DateTime? deadline = null, global::System.Threading.CancellationToken cancellationToken = default(global::System.Threading.CancellationToken))
      {
        return AgregarUsuarioAsync(request, new grpc::CallOptions(headers, deadline, cancellationToken));
      }
      [global::System.CodeDom.Compiler.GeneratedCode("grpc_csharp_plugin", null)]
      public virtual grpc::AsyncUnaryCall<global::GrpcClientAPI.AgregarUsuarioResponse> AgregarUsuarioAsync(global::GrpcClientAPI.AgregarUsuarioRequest request, grpc::CallOptions options)
      {
        return CallInvoker.AsyncUnaryCall(__Method_AgregarUsuario, null, options, request);
      }
      [global::System.CodeDom.Compiler.GeneratedCode("grpc_csharp_plugin", null)]
      public virtual global::GrpcClientAPI.SeguirUsuarioResponse SeguirUsuario(global::GrpcClientAPI.SeguirUsuarioRequest request, grpc::Metadata headers = null, global::System.DateTime? deadline = null, global::System.Threading.CancellationToken cancellationToken = default(global::System.Threading.CancellationToken))
      {
        return SeguirUsuario(request, new grpc::CallOptions(headers, deadline, cancellationToken));
      }
      [global::System.CodeDom.Compiler.GeneratedCode("grpc_csharp_plugin", null)]
      public virtual global::GrpcClientAPI.SeguirUsuarioResponse SeguirUsuario(global::GrpcClientAPI.SeguirUsuarioRequest request, grpc::CallOptions options)
      {
        return CallInvoker.BlockingUnaryCall(__Method_SeguirUsuario, null, options, request);
      }
      [global::System.CodeDom.Compiler.GeneratedCode("grpc_csharp_plugin", null)]
      public virtual grpc::AsyncUnaryCall<global::GrpcClientAPI.SeguirUsuarioResponse> SeguirUsuarioAsync(global::GrpcClientAPI.SeguirUsuarioRequest request, grpc::Metadata headers = null, global::System.DateTime? deadline = null, global::System.Threading.CancellationToken cancellationToken = default(global::System.Threading.CancellationToken))
      {
        return SeguirUsuarioAsync(request, new grpc::CallOptions(headers, deadline, cancellationToken));
      }
      [global::System.CodeDom.Compiler.GeneratedCode("grpc_csharp_plugin", null)]
      public virtual grpc::AsyncUnaryCall<global::GrpcClientAPI.SeguirUsuarioResponse> SeguirUsuarioAsync(global::GrpcClientAPI.SeguirUsuarioRequest request, grpc::CallOptions options)
      {
        return CallInvoker.AsyncUnaryCall(__Method_SeguirUsuario, null, options, request);
      }
      [global::System.CodeDom.Compiler.GeneratedCode("grpc_csharp_plugin", null)]
      public virtual global::GrpcClientAPI.DejarDeSeguirUsuarioResponse DejarDeSeguirUsuario(global::GrpcClientAPI.DejarDeSeguirUsuarioRequest request, grpc::Metadata headers = null, global::System.DateTime? deadline = null, global::System.Threading.CancellationToken cancellationToken = default(global::System.Threading.CancellationToken))
      {
        return DejarDeSeguirUsuario(request, new grpc::CallOptions(headers, deadline, cancellationToken));
      }
      [global::System.CodeDom.Compiler.GeneratedCode("grpc_csharp_plugin", null)]
      public virtual global::GrpcClientAPI.DejarDeSeguirUsuarioResponse DejarDeSeguirUsuario(global::GrpcClientAPI.DejarDeSeguirUsuarioRequest request, grpc::CallOptions options)
      {
        return CallInvoker.BlockingUnaryCall(__Method_DejarDeSeguirUsuario, null, options, request);
      }
      [global::System.CodeDom.Compiler.GeneratedCode("grpc_csharp_plugin", null)]
      public virtual grpc::AsyncUnaryCall<global::GrpcClientAPI.DejarDeSeguirUsuarioResponse> DejarDeSeguirUsuarioAsync(global::GrpcClientAPI.DejarDeSeguirUsuarioRequest request, grpc::Metadata headers = null, global::System.DateTime? deadline = null, global::System.Threading.CancellationToken cancellationToken = default(global::System.Threading.CancellationToken))
      {
        return DejarDeSeguirUsuarioAsync(request, new grpc::CallOptions(headers, deadline, cancellationToken));
      }
      [global::System.CodeDom.Compiler.GeneratedCode("grpc_csharp_plugin", null)]
      public virtual grpc::AsyncUnaryCall<global::GrpcClientAPI.DejarDeSeguirUsuarioResponse> DejarDeSeguirUsuarioAsync(global::GrpcClientAPI.DejarDeSeguirUsuarioRequest request, grpc::CallOptions options)
      {
        return CallInvoker.AsyncUnaryCall(__Method_DejarDeSeguirUsuario, null, options, request);
      }
      [global::System.CodeDom.Compiler.GeneratedCode("grpc_csharp_plugin", null)]
      public virtual global::GrpcClientAPI.TraerUsuariosSeguidosResponse TraerUsuariosSeguidos(global::GrpcClientAPI.TraerUsuariosSeguidosRequest request, grpc::Metadata headers = null, global::System.DateTime? deadline = null, global::System.Threading.CancellationToken cancellationToken = default(global::System.Threading.CancellationToken))
      {
        return TraerUsuariosSeguidos(request, new grpc::CallOptions(headers, deadline, cancellationToken));
      }
      [global::System.CodeDom.Compiler.GeneratedCode("grpc_csharp_plugin", null)]
      public virtual global::GrpcClientAPI.TraerUsuariosSeguidosResponse TraerUsuariosSeguidos(global::GrpcClientAPI.TraerUsuariosSeguidosRequest request, grpc::CallOptions options)
      {
        return CallInvoker.BlockingUnaryCall(__Method_TraerUsuariosSeguidos, null, options, request);
      }
      [global::System.CodeDom.Compiler.GeneratedCode("grpc_csharp_plugin", null)]
      public virtual grpc::AsyncUnaryCall<global::GrpcClientAPI.TraerUsuariosSeguidosResponse> TraerUsuariosSeguidosAsync(global::GrpcClientAPI.TraerUsuariosSeguidosRequest request, grpc::Metadata headers = null, global::System.DateTime? deadline = null, global::System.Threading.CancellationToken cancellationToken = default(global::System.Threading.CancellationToken))
      {
        return TraerUsuariosSeguidosAsync(request, new grpc::CallOptions(headers, deadline, cancellationToken));
      }
      [global::System.CodeDom.Compiler.GeneratedCode("grpc_csharp_plugin", null)]
      public virtual grpc::AsyncUnaryCall<global::GrpcClientAPI.TraerUsuariosSeguidosResponse> TraerUsuariosSeguidosAsync(global::GrpcClientAPI.TraerUsuariosSeguidosRequest request, grpc::CallOptions options)
      {
        return CallInvoker.AsyncUnaryCall(__Method_TraerUsuariosSeguidos, null, options, request);
      }
      /// <summary>Creates a new instance of client from given <c>ClientBaseConfiguration</c>.</summary>
      [global::System.CodeDom.Compiler.GeneratedCode("grpc_csharp_plugin", null)]
      protected override UserClient NewInstance(ClientBaseConfiguration configuration)
      {
        return new UserClient(configuration);
      }
    }

  }
}
#endregion
