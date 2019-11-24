angular.module('employeeportal').controller('EmployeeController', EmployeeController);

EmployeeController.$inject = ['$scope', 'Employee'];

function EmployeeController($scope, Employee){
	
	$scope.employees = Employee.query();

	$scope.employee = {};
	
	$scope.buttonText="Submit";
	var dateObj = new Date();
	$scope.saveEmployee = function() {

		dateObj = new Date($scope.employee.dob);
		var ISOObj = dateObj.toISOString().split('T')[0];
		// var dateArray = ISOObj.split('-');
		// var newString = dateArray[2] +"-"+ dateArray[1] +"-"+ dateArray[0];
		
		document.getElementById("employeeDob").innerHTML = ISOObj;
		
		if ($scope.employee.id !== undefined) {
			Employee.update($scope.employee, function() {
				$scope.employees = Employee.query();
				$scope.employee = {};
				$scope.buttonText="Submit";
			});
		} else {
			Employee.save($scope.employee, function() {
				$scope.employees = Employee.query();
				$scope.employee = {};
			});
		}
		
	}

	$scope.updateEmployee = function(employee) {
		
		$scope.buttonText="Update";
		$scope.employee = employee;
		$scope.employee.dob = new Date(employee.dob);
		dateObj = new Date(employee.dob);
		var ISOObj = dateObj.toISOString().split('T')[0];
		// var dateArray = ISOObj.split('-');
		// var newString = dateArray[2] +"-"+ dateArray[1] +"-"+ dateArray[0];
		// alert(newString);
		document.getElementById("empdob").innerText = ISOObj;
	}

	$scope.deleteEmployee = function(employee) {
		employee.$delete({id: employee.id}, function() {
			$scope.employees = Employee.query();
		});
	}	
}     