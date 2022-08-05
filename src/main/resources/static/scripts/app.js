'use strict';

/**
 * @ngdoc overview
 * @name siteApp
 * @description
 * # siteApp
 *
 * Main module of the application.
 */
angular
  .module('siteApp', [
    'ngAnimate',
    'ngCookies',
    'ngResource',
    'ngRoute',
    'ngSanitize',
    'ngTouch',  
    'angular-growl'     
  ])
  .constant('appConfig', {url:window.location.origin})
  .config(function ($routeProvider) {    
    $routeProvider
      .when('/tienda', {
        templateUrl: 'views/tienda.html',
        controller: 'appController',
        controllerAs: 'tienda'
      })
      .otherwise({
        redirectTo: '/tienda'
      });
  });
