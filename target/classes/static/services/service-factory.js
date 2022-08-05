'use strict';
angular.module('siteApp')
  .factory('serviceFactory', ($http, $resource, appConfig, growl) => {    

    return{
      getUsuarios: fngetUsuarios,
      getMarcas: fngetMarcas,
      getCategorias: fngetCategorias,
      getSubCategoriasByCategoriaId: fngetSubCategoriasByCategoriaId,
      queryProductos: fnqueryProductos
    };

    function fngetUsuarios(result) {      
      $http.get(appConfig.url + '/usuarios').then(response=>{  return result(response.data)});
    }

    function fngetMarcas(empresa, result) {      
      $http.get(appConfig.url + '/marcas/query?empresa='+ empresa).then(response=>{  return result(response.data)});
    }

    function fngetCategorias(empresa,result) {      
      $http.get(appConfig.url + '/categorias/query?empresa='+ empresa).then(response=>{  return result(response.data)});
    }

    function fngetSubCategoriasByCategoriaId(id, result) {    
      $http.get(appConfig.url + '/subcategorias/query?categoria='+ id).then(response=>{  return result(response.data)});
    }

    function fnqueryProductos(empresa, marca, subcategoria, result) {        
      $http.get(appConfig.url + '/productos/query?empresa='+ empresa + '&marca=' + marca + '&subcategoria='+ subcategoria)
      .then(response=>{  return result(response.data)});
    }

    
  });
