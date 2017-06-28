(function() {
	"use strict";
	var MessageApp = angular.module('MessageApp', [ 'Utils','angularTrix' ]);

	MessageApp.controller('MessageCtrl', [ 'Connection', 'PathUtils','LocalStorage', MessageCtrl ]);

	function MessageCtrl(Connection, pathUtils,LocalStorage) {
		var vm = this;
		
		vm.deleteItem = deleteItem;
		vm.addMessage = addMessage;
		
		var events = ['trixInitialize', 'trixChange', 'trixSelectionChange', 'trixFocus', 'trixBlur', 'trixFileAccept', 'trixAttachmentAdd', 'trixAttachmentRemove'];

		getMessages();
		
		function getMessages(){
			var id = LocalStorage.getItem("id");
			var params={
				"id":id
			}
			Connection.get("/message/messages",params,function(res){
				vm.items = res.messages;
			})
		}
		
		function deleteItem(index){
			var params={
				"id":vm.items[index].id
			}
			Connection.delete("/message/message",params,function(res){
				vm.items[index].splice(index,1);
			})
		}
		
		function addMessage(){
			var id = LocalStorage.getItem("id");
			var params={
				"msg":vm.msg,
				"id":id
			};
			Connection.post("/message/message",params,function(res){
				vm.items.push(res.msg);
			})
		}
	}
	MessageApp.filter("parseHTML", function ($sce) {
        return function (text) {
            return $sce.trustAsHtml(text);
        }
    })
})();