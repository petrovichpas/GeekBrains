app = angular.module("cookStarter",[]).controller("cartCtrl", function ($scope, $http){

    $scope.dishQuantity = 1;

    //для тестов
    let token = 'Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxMDAiLCJyb2xlIjpbXSwiZXhwIjoxNjA2NTgzMzY2LCJpYXQiOjE2MDQ3ODMzNjZ9.581A8nejiYxouaeSVr5XbM3M6vMRhj7w9gLWOlYuijA';
    $http.defaults.headers.common.Authorization = token;

    // получаем токен для тестирования
    $scope.login = () => {
        $http.post("https://cookstarter-users-service.herokuapp.com/auth", {username: "100", password: "100"}).then(response => {
            // сохраняем токен
            token = window.localStorage.getItem('Authorization');
            window.localStorage.setItem('Authorization', token);
            // записываем в заголовок полученный токен
            $http.defaults.headers.common.Authorization = token;
        }).catch(data => {console.log(data)});
    }

    let dishesList;
    //показать все блюда в корзине, своя реализация
    // $http.get("http://localhost:8089/api/v1/dish/all").then(response =>{
    //         $scope.dishesList = dishesList = response.data;
    //         $scope.totalSum = dishesList.reduce((a, b) => a += b.price, 0);
    // }, error => {console.error(error)});

    // довить блюда в корзину, для теста
    $scope.cartData = [];
    $scope.addToCart = (id, name, price, picture, count) => {
        $scope.cartData.push({id: id, name: name, price: price, picture: picture, count: count})
    }

    //показать блюда в меню на кукстартер
    if ($http.defaults.headers.common.Authorization == token) {
        $http.get("https://cookstarter-restaurant-service.herokuapp.com/menu/get/1").then(response => {
            $scope.dishesList = dishesList = response.data;
            $scope.totalSum = dishesList.reduce((a, b) => a += b.price, 0);
        }, error => {console.error(error)});
    }

    // офорить заказ, своя реализация
    $scope.createOrder = (name, price, picture) => {
        let dish = {'name': name, 'price': price, 'picture': picture};
        $http.post("http://localhost:8089/api/v1/dish", dish).then(response => {
            $scope.dishesList.push(response.data);
        }, response => {console.error(response);});
    }

    // офорить заказ на кукстартер (пока не знаю как сделать)
    $scope.createOrder = (customerId, restaurantId, status, dateCreated, dishes) => {
        $http.post("https://cs-order-service.herokuapp.com/orders/add").then(response => {
            $scope.orderList.push(response.data);
        }, response => {console.error(response);});
    }

    //удалить блюдо из корзины
    $scope.delete = dish => {
        $http.delete("http://localhost:8089/api/v1/dish/" + dish.id).then(response => {
            $scope.dishesList.splice($scope.dishesList.map(dish => dish.id).indexOf(dish.id), 1);
            $scope.totalSum = dishesList.reduce((a, b) => a += b.price, 0);
        }, error => {console.log(error);});
    }

    //уменьшить количество блюда на 1, своя реализация
    // $scope.minusOne = () => {$scope.dishQuantity > 1 ? $scope.dishQuantity-- : $scope.dishQuantity = 1}

    //уменьшить количество блюда на 1 в основном проекте
    $scope.minusOne = dish => {
        let objIndex = $scope.cartData.findIndex((obj => obj.id == dish.id))
        $scope.cartData[objIndex].count--;
    }

    //увеличить количество блюда на 1, своя реализация
    // $scope.plusOne = () => {$scope.dishQuantity++;}

    //увеличить количество блюда на 1 в основном проекте
    $scope.plusOne = dish => {
        let objIndex = $scope.cartData.findIndex((obj => obj.id == dish.id))
        $scope.cartData[objIndex].count++;
    }
});

