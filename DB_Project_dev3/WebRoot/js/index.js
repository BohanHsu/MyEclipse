(function () {
  var module = angular.module('soccer-stats-guru', ['ngRoute', 'ngResource']);

  var NAV_CLUBS = 2;
  var NAV_PLAYER = 3;
  var NAV_LEAGUE = 0;

  module.config(function ($routeProvider, $locationProvider) {
    $routeProvider
      .when('/', {
        templateUrl: '/league.html',
        controller: 'LeagueTableCtrl'
      })
      .when('/clubs', {
        templateUrl: '/clubs.html',
        controller: 'ClubsCtrl'
      })
      .when('/club/:name', {
        templateUrl: '/club.html',
        controller: 'ClubCtrl'
      })
      .when('/player', {
        templateUrl: '/player.html',
        controller: 'PlayerCtrl'
      })
      .when('/match', {
        templateUrl: '/match.html',
        controller: 'MatchCtrl'
      });

    $locationProvider.html5Mode(true);
  });

  module.controller('PlayerCtrl', ['$scope', '$routeParams', function ($scope, $routeParams) {
    $scope.onClickNav(NAV_PLAYER);
  }]);

  module.controller('MatchCtrl', ['$scope', '$routeParams', function ($scope) {
    console.log('ok');
  }]);

  module.controller('ClubCtrl', ['$scope', '$routeParams', function ($scope, $routeParams) {
    $scope.onClickNav(NAV_CLUBS);

    $scope.name = $routeParams.name;

    $scope.club = {
      name: $routeParams.name,
      players: [
        {
          name: 'Fu Qiang',
          age: 25,
          number: 9
        }
      ]
    };
  }]);

  module.controller('ClubsCtrl', ['$scope', function ($scope) {
    $scope.onClickNav(NAV_CLUBS);

    $scope.leagues = [
      {
        name: 'Premier League',
        clubs: [
          {
            name: 'Manchester United'
          },
          {
            name: 'Arsenal'
          }
        ]
      }
    ];
  }]);

  module.controller('MainCtrl', ['$scope', function ($scope) {
    $scope.navs = [
      {
        title: 'League Stats',
        url: '/'
      },
      {
        title: 'Match Facts',
        url: '/match'
      },
      {
        title: 'Clubs',
        url: '/clubs'
      },
      {
        title: 'Players',
        url: '/player'
      }
    ];

    $scope.onClickNav = function (index) {
      $.each($scope.navs, function (i, nav) {
        nav.active = (i === index);
      });
    };

    $scope.contentPage = $scope.navs[0].url;
  }]);


  module.controller('LeagueTableCtrl', ['$scope', function ($scope) {
    $scope.onClickNav(NAV_LEAGUE);

    $scope.leagues = [
      {
        icon: '/img/premier-league.gif',
        name: 'Premier League'
      },
      {
        icon: '/img/bundesliga.jpg',
        name: 'Bundes Liga'
      }
    ];

    $scope.teams = [
      {
        name: 'Manchester United',
        gamesPlayed: 10,
        wins: 8,
        draws: 1,
        losses: 1,
        goalsFor: 50,
        goalsAgainst: 15
      },
      {
        name: 'Arsenal',
        gamesPlayed: 10,
        wins: 9,
        draws: 1,
        losses: 0,
        goalsFor: 10,
        goalsAgainst: 15
      }
    ];

    $scope.players = [
      {
        name: 'Fu Qiang',
        team: 'Arsenal',
        goals: 36
      },
      {
        name: 'Peng Qiqi',
        team: 'Arsenal',
        goals: 60
      }
    ];

    $scope.teamPoints = function (team) {
      return team.wins * 3 + team.draws;
    };

    $scope.onClickLeague = function (league) {
      console.log('click on league', league);
    };
  }]);


  module.factory('Player', ['$resource', function ($resource) {
    return $resource('/rest', {}, {

    });
  }]);
}());
