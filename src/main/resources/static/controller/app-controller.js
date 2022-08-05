angular.module('siteApp')
  .controller('appController', function($scope, serviceFactory, growl) {
    /** Seteo de Variables Inicials **/
    $scope.titulo="Tienda Virtual Calle 80";
    $scope.empresa = 1;
    $scope.usuarios =[];
    $scope.categorias =[];
    $scope.subCategorias =[];
    $scope.productos=[];
    $scope.listaProductoCarro=[];
    $scope.totalCompra = 0;    

    //consulta al servicio rest las Categorias de productos x empresa
    serviceFactory.getCategorias($scope.empresa,(response)=>{                  
      $scope.productos=[];
      $scope.subCategorias =[];
      $scope.categorias =response;
    });
    
    //consulta al servicio rest las Marcas de productos x empresa
    serviceFactory.getMarcas($scope.empresa, (response)=>{                 
      $scope.productos=[];
      $scope.marcas =response;
    });  

    //consulta las subcategorias dada la categoria seleccionada en el select del html
    $scope.getSubCategoriasByCategoriaId = function(categoriaId){
      $scope.productos=[];      
      serviceFactory.getSubCategoriasByCategoriaId(categoriaId, (response)=>{                            
        $scope.subCategorias =response;
      });  
    };  

    //Consulta de productos por Empresa, marca y categoria
    $scope.queryProductos = function(marcaId){      
      serviceFactory.queryProductos($scope.empresa, $scope.marca.id, $scope.subCategoria.id, (response)=>{                            
        $scope.productos =response;
      });  
    };  

    //Metodo para agregar producto al carro, tambien actualiza el stock de los productos ofertados
    $scope.addProductoCarro = function(producto){        
      producto.enStock =  $scope.productos.find(x=> x.id == producto.id).enStock;
      if(producto.enStock == 0 ){
        growl.warning("Ups..!, se agotaron las existencias", {
          title: 'Existencias agotadas', ttl:2000,disableCountDown: true
        }); 
        return;
      } 
      //establece el nuevo producto a adicionar al carro
      let p={
        id : producto.id,
        codigo: producto.codigo,
        descripcion :producto.descripcion,                        
        valor:$scope.productos.find(x=> x.id == producto.id).valor,
        cantidad:1,
        enStock: producto.enStock-1 
      };     
      try {
        //Si ya hay productos en el carro, verifica si se encuetra el producto a adicionar para actualizar la cantida y valor        
        let existe = $scope.listaProductoCarro.find(x=> x.id == p.id);                
        if(existe){
          $scope.listaProductoCarro.forEach((item)=>{
            if(item.id == p.id){
              item.valor +=  p.valor;
              item.cantidad ++ ;
              $scope.totalCompra += p.valor;                                      
            }
          });          
        }else{
          $scope.listaProductoCarro.push(p);          
          $scope.totalCompra += p.valor;
        }
        //Actualiza el stock de cada producto ofertado        
        $scope.productos.forEach((item)=>{
          if(item.id == p.id){
            item.enStock--;
          }
        });
      } catch (error) {
        console.log(error);
      }
    };  

    //Metodo para remover un producto del carro, tambien actualiza el stock de los productos ofertados
    $scope.removeProductoCarro = function(producto){
      let id = producto.id;
      let existe = $scope.listaProductoCarro.find(x=> x.id == id);
      let valor = $scope.productos.find(x=> x.id == id).valor;
      if(existe){
        //decrementa el sctock del producto ofertado
        $scope.productos.forEach((item)=>{
          if(item.id == id){
            item.enStock ++;
          }
        });
        if (existe.cantidad==1){
          if(!window.confirm('¿Desea Eliminar este producto del carro de compras.?')){                        
            return;
          }else{
            $scope.listaProductoCarro = $scope.listaProductoCarro.filter(x=> x.id != id);
            $scope.totalCompra -= valor;
            producto.enStock ++;
          }
        }
        //Actualiza el producto del carro de compras
        $scope.listaProductoCarro.forEach((item)=>{
          if(item.id == id){
            item.valor -= valor ;
            item.cantidad -- ;
            $scope.totalCompra -= valor;
            //producto.enStock ++;
          }
        });
      }      
    };    
  });
