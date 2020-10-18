app = angular.module("myApp",[])
    .controller("cartController", function ($scope, $http){

        $scope.dishQuantity = 1;

        //показать все блюда в корзине
        $http.get("http://localhost:8080/api/v1/dish/all")
            .success(function(allDishes){
            $scope.dishesList = allDishes;})
            .error(function(){
            console.log("Что-то пошло не так.")
        });

        //уменьшить количество блюда на 1
        $scope.minusOne = function (number){
            $scope.dishQuantity > 1 ? $scope.dishQuantity-- : $scope.dishQuantity = 1;
        }

        //увеличить количество блюда на 1
        $scope.plusOne = function (number){
            $scope.dishQuantity++;
        }

        //удалить блюдо из корзины
        $scope.delete = function (dish) {
            $http.delete("http://localhost:8080/api/v1/dish/" + dish.id)
                .then(resp => {
                        let ix = $scope.dishesList.map(dish => dish.id).indexOf(dish.id);
                        $scope.dishesList.splice(ix, 1);
                    },
                    resp => {
                        console.error(resp);
                    });
        }
});

