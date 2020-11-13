app = angular.module("cookStarter", ["ngRoute"])
    .config(function ($routeProvider) {
        $routeProvider.when("/menu", {
            templateUrl: "menu.html",
            controller: 'mainCtrl'
        })
        .when("/cart", {
            templateUrl: "cart.htm",
            controller: 'mainCtrl'
        })
    }).controller("mainCtrl", function ($scope, $http){

    let token = $scope.token = 'Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxMDAiLCJyb2xlIjpbXSwiZXhwIjoxNjA2NzI0OTk2LCJpYXQiOjE2MDQ5MjQ5OTZ9.esEjU86X19e2TbYXCl8zYtkHD5OGLWe-v5nGi0X_Z4k';
    $http.defaults.headers.common.Authorization = token;

    // получаем токен
    $scope.login = () => {
        $http.post("https://cookstarter-users-service.herokuapp.com/auth", {username: "100", password: "100"}).then(response => {
            // сохраняем токен
            window.localStorage.setItem('Authorization', response.data.token);
            token = window.localStorage.getItem('Authorization');
            // записываем в заголовок полученный токен
            $http.defaults.headers.common.Authorization = token;
        }).catch(data => {console.log(data)});
    }

    //получаем информацию о ресторане № 2
        if ($http.defaults.headers.common.Authorization == token) {
            $http.get("https://cookstarter-restaurant-service.herokuapp.com/restaurant/get/2").then(response => {
                $scope.restaurant = response.data;
            }, error => {
                console.error(error)
            });
        }

    //показать все блюда из меню ресторана 2
    if ($http.defaults.headers.common.Authorization == token) {
        $http.get("https://cookstarter-restaurant-service.herokuapp.com/menu/get/2").then(response => {
            $scope.dishesList = response.data;
        }, error => {console.error(error)});
    }

    // довить блюдо в корзину, для теста
    $scope.cartData = []
    // let cartData = []
    // $scope.totalSum = $scope.cartData.reduce((a, b) => a += b.price, 0);
    $scope.addToCart = (d, quantity) => {
        if ($scope.cartData.some(obj => obj.id === d.id)){
            $scope.plusOne(d);
        } else {
            $scope.cartData.push({id: d.id, name: d.name, price: d.price, description: d.description, pictureId: d.pictureId, restaurantId: d.restaurantId, quantity: quantity})
            $scope.totalSum = $scope.cartData.reduce((a, b) => a += b.price, 0);
        }
        console.log($scope.cartData)
        // localStorage.setItem("cartData", JSON.stringify(cartData))
        // localStorage.setItem("totalSum", totalSum)

        // $scope.totalSum = localStorage.getItem('totalSum')
        // $scope.cartData = localStorage.getItem('cartData')
    }

    //удалить блюдо из корзины
    $scope.delete = dish => {
        $scope.cartData.splice($scope.cartData.map(dish => dish.id).indexOf(dish.id), 1);
        $scope.totalSum = $scope.cartData.reduce((a, b) => a += b.price * b.quantity, 0);

    }

    //уменьшить количество блюда на 1 в основном проекте
    $scope.minusOne = dish => {
        let objIndex = $scope.cartData.findIndex((obj => obj.id === dish.id))
        if ($scope.cartData[objIndex].quantity > 1) {
            $scope.cartData[objIndex].quantity--;
            $scope.totalSum -= dish.price;
        } else {
            $scope.cartData[objIndex].quantity = 1;
        }
    }

    //увеличить количество блюда на 1 в основном проекте
    $scope.plusOne = dish => {
        let objIndex = $scope.cartData.findIndex((obj => obj.id === dish.id))
        $scope.cartData[objIndex].quantity++;
        $scope.totalSum += dish.price;
    }

        // офорить заказ на кукстартер
        $scope.addOrder = (cartData, customerId) => { //customerId недоступен, поправить ответ от бэка
            $http.post("https://cs-order-service.herokuapp.com/orders/add", {
                customerId: customerId,
                restaurantId: cartData.restaurantId,
                dishes: {
                    1: { // как поставить вместо "1" "cartData.id" ?
                        price: cartData.price,
                        quantity: cartData.quantity
                    }
                }
            }).then(response => {
                console.log(response)
            }, response => {
                console.error(response);
            });
        }
});


// ===================================================================================================================
// app.controller("cartCtrl", function ($scope, $http, menuFactory) {
//     $scope.dishQuantity = 1;
//     $scope.totalSum = menuFactory.totalSum;
//
//     //для тестов
//     let token = 'Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxMDAiLCJyb2xlIjpbXSwiZXhwIjoxNjA2NzI0OTk2LCJpYXQiOjE2MDQ5MjQ5OTZ9.esEjU86X19e2TbYXCl8zYtkHD5OGLWe-v5nGi0X_Z4k'
//     // let token = '';
//     $http.defaults.headers.common.Authorization = token;
//
//     // получаем токен для тестирования
//     $scope.login = () => {
//         $http.post("https://cookstarter-users-service.herokuapp.com/auth", {
//             username: "100",
//             password: "100"
//         }).then(response => {
//             // сохраняем токен
//             token = 'Bearer ' + response.data.token;
//             window.localStorage.setItem('Authorization', token);
//             // записываем в заголовок полученный токен
//             $http.defaults.headers.common.Authorization = token;
//         }).catch(data => {console.log(data)});
//     }
//
//     // офорить заказ, своя реализация
//     $scope.createOrder = (name, price, picture) => {
//         let dish = {'name': name, 'price': price, 'picture': picture};
//         $http.post("http://localhost:8089/api/v1/dish", dish).then(response => {
//             $scope.dishesList.push(response.data);
//         }, response => {
//             console.error(response);
//         });
//     }
//
//     // офорить заказ на кукстартер (пока не знаю как сделать)
//     $scope.createOrder = (customerId, restaurantId, status, dateCreated, dishes) => {
//         // $http.post("https://cs-order-service.herokuapp.com/orders/add").then(response => {
//         //     $scope.orderList.push(response.data);
//         // }, response => {
//         //     console.error(response);
//         // });
//         console.log(window.localStorage.getItem('cartData'))
//     }
//
//     //удалить блюдо из корзины
//     $scope.delete = dish => {
//         $http.delete("http://localhost:8089/api/v1/dish/" + dish.id).then(response => {
//             $scope.dishesList.splice($scope.dishesList.map(dish => dish.id).indexOf(dish.id), 1);
//             $scope.totalSum = dishesList.reduce((a, b) => a += b.price, 0);
//         }, error => {
//             console.log(error);
//         });
//     }
//
//     //уменьшить количество блюда на 1 в основном проекте
//     $scope.minusOne = dish => {
//         let objIndex = $scope.cartData.findIndex((obj => obj.id == dish.id))
//         $scope.cartData[objIndex].count--;
//     }
//
//     //увеличить количество блюда на 1 в основном проекте
//     $scope.plusOne = dish => {
//         let objIndex = $scope.cartData.findIndex((obj => obj.id == dish.id))
//         $scope.cartData[objIndex].count++;
//     }
// });
//
// app.factory('menuFactory', totalSum => {
//     return {
//         cartData: this,
//         totalSum: totalSum,
//     }
// });