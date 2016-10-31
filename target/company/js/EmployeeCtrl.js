/**
 * 
 */

var app = angular.module('myApp', [ "ngTable"]);
app.controller('myCtrl',function($scope, $http, $filter,$sce, NgTableParams) {
			$http.get("http://localhost:8081/company/Employees").then(
				function(response) {
					$scope.employees = response.data;
				 /* $scope.$watch("data", function () {
						$scope.empTable.reload();
					});  */
					$scope.empTable = new NgTableParams(
					{
						page : 1,
						count : 10
					},
					{
						total : $scope.employees.length,
						getData : function(params) {
							/* $scope.data = params.sorting() ? $filter('orderBy')($scope.employees,params.orderBy()): $scope.data;
							   $scope.data = params.filter() ? $filter('filter')($scope.data,params.filter()): $scope.data; */
							   $scope.data = ($scope.employees).slice((params.page() - 1)* params.count(),params.page()* params.count());
							   
							return $scope.data;
						},
						
				});

		});
			
			
		$scope.filterHeader= function(title) {
			if(title.length<=1) return title.toUpperCase();
			result=title[0].toUpperCase();
			for(var i=1;i<title.length;i++){
				if(title[i]==title[i].toUpperCase())
					result+=' '+title[i].toUpperCase();
				else
					result+=title[i];
			}
			return result;
		}
							 
		/*					
		$scope.addEmployee= function(){
			$scope.data.unshift( // insert fake object at the begging of data array 
			{
				"id": $sce.trustAsHtml("<input style=\"width:40px;\" type=\"text\" placeholder=\"Id\" ng-model=\"tempEmp.id\" />"),
				"cnp": $sce.trustAsHtml("<input style=\"width:40px;\" type=\"text\" placeholder=\"Cnp\" ng-model=\"tempEmp.cnp\"/>"),
				"firstName": $sce.trustAsHtml("<input style=\"width:90px;\" type=\"text\" placeholder=\"First Name\" ng-model=\"tempEmp.firstName\"/>"),
				"lastName": $sce.trustAsHtml("<input style=\"width:90px;\" type=\"text\" placeholder=\"Last Name\" ng-model=\"tempEmp.lastName\"/>"),
				"dateOfBirth": $sce.trustAsHtml("<input style=\"width:80px;\" type=\"text\" placeholder=\"Date of birth\" ng-model=\"tempEmp.dateOfBirth\"/>"),
				"address": $sce.trustAsHtml("<input style=\"width:90px;\" type=\"text\" placeholder=\"Adress\" ng-model=\"tempEmp.address\"/>"),
				"married": $sce.trustAsHtml("<select ng-model=\"tempEmp.married\"><option value=\"false\" selected>false</option><option value=\"true\" >true</option> </select>"),
				"blackListed": $sce.trustAsHtml("<select ng-model=\"tempEmp.blackListed\"><option value=\"false\" selected>false</option><option value=\"true\" >true</option></select> ")  
			});
		}*/
		
		
		$scope.addEmployee = function(){
			$scope.empl=jQuery.extend(true, {}, $scope.defaultEmpl);
			$("#emplFormTitle").text("Add Employee");
			$scope.displayForm();
			
			if($scope.lastIndex%2!=0)
				$('#dataTable').find("tbody tr:eq("+$scope.lastIndex+")").css("background","rgb(236, 240, 241)");
			else
				$('#dataTable').find("tbody tr:eq("+$scope.lastIndex+")").css("background","white");
			$scope.lastIndex=-1;
			$scope.flag="add";
		}
							
		$scope.displayForm= function(){
								
			$("#dataTable").removeClass("col-md-offset-2");
			$("#dataTable").removeClass("col-md-8");
			$("#dataTable").addClass("col-md-7");
								
			var box=$("#form");
			if (box.hasClass('hidden')) {
								    
				box.removeClass('hidden');
				setTimeout(function () {
					box.removeClass('visuallyhidden');
				}, 150);
					setTimeout(function () {
					box.removeClass('visuallyhidden2');
				}, 250);
								    
			}					
								
				/* $("#dataTable").removeClass("col-md-offset-2");
				   $("#dataTable").removeClass("col-md-8");
				   $("#dataTable").addClass("col-md-7"); */
				//$(".form").css({"display" : "inline"});
		}//end of displayForm
							
		$scope.to_trusted = function(html_code) {
			return $sce.trustAsHtml(html_code.toString());
		}
							
		$scope.removeRow = function ($index) {
								
			$http({url: "http://localhost:8081/company/Employee/delete", 
				   method: "DELETE",
				   params: {id: $scope.data[$index].id}
			}).then(function(response){
				   console.log("Success at delete!");
				   $("#serverResponse").css({"color":"green"});
				   $("#serverResponse").text("Success at delete!");
									 
				   $scope.data.splice($index,1);
				   $scope.employees.splice($index,1);
			},function(response){
				   console.log("Failure at delete!");
				   $("#serverResponse").css({"color":"red"});
				   $("#serverResponse").text("Failure at delete!");
			});
			
			if($index==$scope.lastIndex)
				if($scope.lastIndex%2!=0)
					$('#dataTable').find("tbody tr:eq("+$scope.lastIndex+")").css("background","rgb(236, 240, 241)");
				else
					$('#dataTable').find("tbody tr:eq("+$scope.lastIndex+")").css("background","white");
			
								
			/* console.log($scope.data[$index]);
			$scope.data.splice($index, 1 ); */
		}
		
		$scope.reset = function ( obj){
			if($scope.flag=="edit")
				$scope.empl=jQuery.extend(true, {}, $scope.data[$scope.lastIndex]);
			else
				$scope.empl=jQuery.extend(true,{}, $scope.defaultEmpl);
		}
		
		$scope.editRow = function ($index) {
			
			$("#emplFormTitle").text("Edit Employee");
			$scope.empl = jQuery.extend(true, {}, $scope.data[$index]);
			$('#dataTable').find("tbody tr:eq("+$index+")").css("background","#F5ECCE");
			if($scope.lastIndex%2!=0)
				$('#dataTable').find("tbody tr:eq("+$scope.lastIndex+")").css("background","rgb(236, 240, 241)");
			else
				$('#dataTable').find("tbody tr:eq("+$scope.lastIndex+")").css("background","white");
			$scope.lastIndex=$index;
			$scope.displayForm();
			$scope.flag="edit";
		}
							
		$scope.truthOptions=[{name:"True",value:true},{name:"False",value:false}];
		
		$scope.empl={}
		
		$scope.defaultEmpl={
			id: '',
			cnp: '',
			firstName: '',
			lastName: '',
			dateOfBirth: '',
			address: '',
			married: '',
			blackListed: '' 
		}
		
		$( '.scrollable' ).on( 'mousewheel DOMMouseScroll', function ( e ) {
		    var e0 = e.originalEvent,
		        delta = e0.wheelDelta || -e0.detail;

		    this.scrollTop += ( delta < 0 ? 1 : -1 ) * 30;
		    e.preventDefault();
		});
		
		$scope.submitEmployee = function(empl){
			console.log(empl);
		}
		
		$(document).ready(function(){
			$('#dateOfBirth').datepicker({
			    format: "dd-mm-yyyy",
			    autoclose: true,
			    toggleActive: true
			});
			
			$("#dateOfBirth").on("change", function() {
				var controllerElement = document.querySelector('#app');
				var controllerScope = angular.element(controllerElement).scope();
				controllerScope.$apply(function(){
					controllerScope.empl.dateOfBirth= $("#dateOfBirth").val();
					console.log(controllerScope.empl);
				});
//		        angular.$scope.empl.dateOfBirth = $("#dateOfBirth").val();
//				console.log($("#dateOfBirth").val());
		    });
		});
		
});
