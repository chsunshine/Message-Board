(function() {
	"use strict";
	var loginApp = angular.module('LoginApp', [ 'Utils' ]);

	loginApp.controller('LoginCtrl', [ 'Connection', 'PathUtils','LocalStorage', LoginCtrl ]);

	function LoginCtrl(conn, pathUtils,LocalStorage) {
		var vm = this;

		vm.submit = submit;

		function submit() {
			var param = {
				"username" : vm.username,
				"password" : vm.password
			}
			conn.post("user/login", param, function(res) {
				if (res.state == 0) {
					alert(res.msg);
				} else if (res.state == 1) {
					LocalStorage.setItem("id",res.id);
					window.location.href = "http://localhost:8080/message.html";
				}
			})
		}
	}
})();