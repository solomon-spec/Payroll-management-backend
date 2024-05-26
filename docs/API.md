# OpenAPI definition
## Version: v0

### /api/salary-status/{id}

#### PUT
##### Summary:

Update a salary payment status

##### Description:

Updates an existing salary payment status record

##### Parameters

| Name | Located in | Description | Required | Schema |
| ---- | ---------- | ----------- | -------- | ---- |
| id | path |  | Yes | long |

##### Responses

| Code | Description |
| ---- | ----------- |
| 200 | OK |
| 400 | Bad Request |

### /api/salaries/{id}

#### GET
##### Summary:

Get a salary by ID

##### Description:

Retrieves the details of a salary record by its ID

##### Parameters

| Name | Located in | Description | Required | Schema |
| ---- | ---------- | ----------- | -------- | ---- |
| id | path |  | Yes | long |

##### Responses

| Code | Description |
| ---- | ----------- |
| 200 | OK |
| 400 | Bad Request |

#### PUT
##### Summary:

Update a salary

##### Description:

Updates an existing salary record with new data

##### Parameters

| Name | Located in | Description | Required | Schema |
| ---- | ---------- | ----------- | -------- | ---- |
| id | path |  | Yes | long |

##### Responses

| Code | Description |
| ---- | ----------- |
| 200 | OK |
| 400 | Bad Request |

#### DELETE
##### Summary:

Delete a salary

##### Description:

Deletes a salary record from the system

##### Parameters

| Name | Located in | Description | Required | Schema |
| ---- | ---------- | ----------- | -------- | ---- |
| id | path |  | Yes | long |

##### Responses

| Code | Description |
| ---- | ----------- |
| 200 | OK |
| 400 | Bad Request |

### /api/leave/{id}

#### GET
##### Summary:

Get a leave record by ID

##### Description:

Retrieves the details of a specific leave record by its ID

##### Parameters

| Name | Located in | Description | Required | Schema |
| ---- | ---------- | ----------- | -------- | ---- |
| id | path |  | Yes | long |

##### Responses

| Code | Description |
| ---- | ----------- |
| 200 | OK |
| 400 | Bad Request |

#### PUT
##### Summary:

Update a leave record

##### Description:

Updates the details of a specific leave record

##### Parameters

| Name | Located in | Description | Required | Schema |
| ---- | ---------- | ----------- | -------- | ---- |
| id | path |  | Yes | long |

##### Responses

| Code | Description |
| ---- | ----------- |
| 200 | OK |
| 400 | Bad Request |

#### DELETE
##### Summary:

Delete a leave record

##### Description:

Deletes a specific leave record by its ID

##### Parameters

| Name | Located in | Description | Required | Schema |
| ---- | ---------- | ----------- | -------- | ---- |
| id | path |  | Yes | long |

##### Responses

| Code | Description |
| ---- | ----------- |
| 200 | OK |
| 400 | Bad Request |

### /api/leave/{id}/reject

#### PUT
##### Summary:

Reject a leave request

##### Description:

Rejects a pending leave request

##### Parameters

| Name | Located in | Description | Required | Schema |
| ---- | ---------- | ----------- | -------- | ---- |
| id | path |  | Yes | long |

##### Responses

| Code | Description |
| ---- | ----------- |
| 200 | OK |
| 400 | Bad Request |

### /api/leave/{id}/cancel

#### PUT
##### Summary:

Cancel a leave request

##### Description:

Cancels a pending or approved leave request

##### Parameters

| Name | Located in | Description | Required | Schema |
| ---- | ---------- | ----------- | -------- | ---- |
| id | path |  | Yes | long |

##### Responses

| Code | Description |
| ---- | ----------- |
| 200 | OK |
| 400 | Bad Request |

### /api/leave/{id}/approve

#### PUT
##### Summary:

Approve a leave request

##### Description:

Approves a pending leave request

##### Parameters

| Name | Located in | Description | Required | Schema |
| ---- | ---------- | ----------- | -------- | ---- |
| id | path |  | Yes | long |

##### Responses

| Code | Description |
| ---- | ----------- |
| 200 | OK |
| 400 | Bad Request |

### /api/employee/{id}

#### GET
##### Summary:

Get an employee by ID

##### Description:

Retrieves the details of an employee record by its ID

##### Parameters

| Name | Located in | Description | Required | Schema |
| ---- | ---------- | ----------- | -------- | ---- |
| id | path |  | Yes | long |

##### Responses

| Code | Description |
| ---- | ----------- |
| 200 | OK |
| 400 | Bad Request |

#### PUT
##### Summary:

Update an employee

##### Description:

Updates an existing employee record with new data

##### Parameters

| Name | Located in | Description | Required | Schema |
| ---- | ---------- | ----------- | -------- | ---- |
| id | path |  | Yes | long |

##### Responses

| Code | Description |
| ---- | ----------- |
| 200 | OK |
| 400 | Bad Request |

#### DELETE
##### Summary:

Delete an employee

##### Description:

Deletes an employee record from the system

##### Parameters

| Name | Located in | Description | Required | Schema |
| ---- | ---------- | ----------- | -------- | ---- |
| id | path |  | Yes | long |

##### Responses

| Code | Description |
| ---- | ----------- |
| 200 | OK |
| 400 | Bad Request |

### /api/employee/assign-manager/{employeeId}

#### PUT
##### Summary:

Assign a manager to an employee

##### Description:

Assigns a manager to a specific employee

##### Parameters

| Name | Located in | Description | Required | Schema |
| ---- | ---------- | ----------- | -------- | ---- |
| employeeId | path |  | Yes | long |

##### Responses

| Code | Description |
| ---- | ----------- |
| 200 | OK |
| 400 | Bad Request |

### /api/attendance-policy/{id}

#### GET
##### Summary:

Get an attendance policy by ID

##### Description:

Retrieves the details of an attendance policy by its ID

##### Parameters

| Name | Located in | Description | Required | Schema |
| ---- | ---------- | ----------- | -------- | ---- |
| id | path |  | Yes | long |

##### Responses

| Code | Description |
| ---- | ----------- |
| 200 | OK |
| 400 | Bad Request |

#### PUT
##### Summary:

Update an attendance policy

##### Description:

Updates an existing attendance policy with new data

##### Parameters

| Name | Located in | Description | Required | Schema |
| ---- | ---------- | ----------- | -------- | ---- |
| id | path |  | Yes | long |

##### Responses

| Code | Description |
| ---- | ----------- |
| 200 | OK |
| 400 | Bad Request |

#### DELETE
##### Summary:

Delete an attendance policy

##### Description:

Deletes an attendance policy from the system

##### Parameters

| Name | Located in | Description | Required | Schema |
| ---- | ---------- | ----------- | -------- | ---- |
| id | path |  | Yes | long |

##### Responses

| Code | Description |
| ---- | ----------- |
| 200 | OK |
| 400 | Bad Request |

### /api/salary-status/

#### POST
##### Summary:

Create a salary payment status

##### Description:

Creates a new salary payment status record

##### Responses

| Code | Description |
| ---- | ----------- |
| 200 | OK |
| 400 | Bad Request |

### /api/salaries

#### GET
##### Summary:

Get all salaries

##### Description:

Retrieves all salary records in the system

##### Responses

| Code | Description |
| ---- | ----------- |
| 200 | OK |
| 400 | Bad Request |

#### POST
##### Summary:

Create a salary

##### Description:

Creates a new salary record in the system

##### Responses

| Code | Description |
| ---- | ----------- |
| 200 | OK |
| 400 | Bad Request |

### /api/leave

#### POST
##### Summary:

Create a leave record

##### Description:

Creates a new leave record for an employee

##### Responses

| Code | Description |
| ---- | ----------- |
| 200 | OK |
| 400 | Bad Request |

### /api/employee

#### POST
##### Summary:

Create a new employee

##### Description:

Creates a new employee record in the system

##### Responses

| Code | Description |
| ---- | ----------- |
| 200 | OK |
| 400 | Bad Request |

### /api/attendance-record/employees/{employeeId}/check-in

#### POST
##### Summary:

Check in an employee

##### Description:

Checks in an employee and creates a new attendance record in the system

##### Parameters

| Name | Located in | Description | Required | Schema |
| ---- | ---------- | ----------- | -------- | ---- |
| employeeId | path |  | Yes | long |

##### Responses

| Code | Description |
| ---- | ----------- |
| 200 | OK |
| 400 | Bad Request |

### /api/attendance-policy

#### POST
##### Summary:

Create a new attendance policy

##### Description:

Creates a new attendance policy in the system

##### Responses

| Code | Description |
| ---- | ----------- |
| 200 | OK |
| 400 | Bad Request |

### /api/attendance-record/employees/{employeeId}/check-out

#### PATCH
##### Summary:

Check out an employee

##### Description:

Checks out an employee and updates the corresponding attendance record

##### Parameters

| Name | Located in | Description | Required | Schema |
| ---- | ---------- | ----------- | -------- | ---- |
| employeeId | path |  | Yes | long |

##### Responses

| Code | Description |
| ---- | ----------- |
| 200 | OK |
| 400 | Bad Request |

### /api/salary-status/time

#### GET
##### Summary:

Get salary payment statuses by date range

##### Description:

Retrieves all salary payment statuses within a specified date range

##### Parameters

| Name | Located in | Description | Required | Schema |
| ---- | ---------- | ----------- | -------- | ---- |
| startDate | query |  | Yes | date |
| endDate | query |  | Yes | date |

##### Responses

| Code | Description |
| ---- | ----------- |
| 200 | OK |
| 400 | Bad Request |

### /api/salary-status/status

#### GET
##### Summary:

Get salary payment statuses by status

##### Description:

Retrieves all salary payment statuses with a specific status (e.g., paid, pending)

##### Parameters

| Name | Located in | Description | Required | Schema |
| ---- | ---------- | ----------- | -------- | ---- |
| status | query |  | Yes | string |

##### Responses

| Code | Description |
| ---- | ----------- |
| 200 | OK |
| 400 | Bad Request |

### /api/salary-status/employee/{employeeId}

#### GET
##### Summary:

Get salary payment statuses by employee ID

##### Description:

Retrieves all salary payment statuses for a specific employee

##### Parameters

| Name | Located in | Description | Required | Schema |
| ---- | ---------- | ----------- | -------- | ---- |
| employeeId | path |  | Yes | long |

##### Responses

| Code | Description |
| ---- | ----------- |
| 200 | OK |
| 400 | Bad Request |

### /api/salaries/employee/{employeeId}

#### GET
##### Summary:

Get salaries by employee ID

##### Description:

Retrieves all salary records associated with a specific employee

##### Parameters

| Name | Located in | Description | Required | Schema |
| ---- | ---------- | ----------- | -------- | ---- |
| employeeId | path |  | Yes | long |

##### Responses

| Code | Description |
| ---- | ----------- |
| 200 | OK |
| 400 | Bad Request |

### /api/leave/search

#### GET
##### Summary:

Search for leave records

##### Description:

Searches for leave records based on criteria such as employee ID, status, date range, etc.

##### Parameters

| Name | Located in | Description | Required | Schema |
| ---- | ---------- | ----------- | -------- | ---- |
| employeeId | query |  | No | long |
| status | query |  | No | string |
| startDate | query |  | No | date |
| endDate | query |  | No | date |

##### Responses

| Code | Description |
| ---- | ----------- |
| 200 | OK |
| 400 | Bad Request |

### /api/leave/employee/{employeeId}

#### GET
##### Summary:

Get leave records by employee ID

##### Description:

Retrieves all leave records for a specific employee

##### Parameters

| Name | Located in | Description | Required | Schema |
| ---- | ---------- | ----------- | -------- | ---- |
| employeeId | path |  | Yes | long |

##### Responses

| Code | Description |
| ---- | ----------- |
| 200 | OK |
| 400 | Bad Request |

### /api/leave/balance/{employeeId}

#### GET
##### Summary:

Get leave balance

##### Description:

Retrieves the remaining leave balance for an employee

##### Parameters

| Name | Located in | Description | Required | Schema |
| ---- | ---------- | ----------- | -------- | ---- |
| employeeId | path |  | Yes | long |

##### Responses

| Code | Description |
| ---- | ----------- |
| 200 | OK |
| 400 | Bad Request |

### /api/leave/all

#### GET
##### Summary:

Get all leave records

##### Description:

Retrieves all leave records in the system

##### Responses

| Code | Description |
| ---- | ----------- |
| 200 | OK |
| 400 | Bad Request |

### /api/employee/search

#### GET
##### Summary:

Search for employees

##### Description:

Searches for employees based on criteria such as name, email, position, phone number, date of joining

##### Parameters

| Name | Located in | Description | Required | Schema |
| ---- | ---------- | ----------- | -------- | ---- |
| name | query |  | No | string |
| email | query |  | No | string |
| position | query |  | No | string |
| phoneNumber | query |  | No | string |
| dateOfJoiningMin | query |  | No | string |
| dateOfJoiningMax | query |  | No | string |

##### Responses

| Code | Description |
| ---- | ----------- |
| 200 | OK |
| 400 | Bad Request |

### /api/employee/on-leave

#### GET
##### Summary:

Get employees on leave

##### Description:

Retrieves all employees who are currently on leave

##### Responses

| Code | Description |
| ---- | ----------- |
| 200 | OK |
| 400 | Bad Request |

### /api/employee/by-manager/{managerId}

#### GET
##### Summary:

Get employees on leave

##### Description:

Retrieves all employees who are currently on leave

##### Parameters

| Name | Located in | Description | Required | Schema |
| ---- | ---------- | ----------- | -------- | ---- |
| managerId | path |  | Yes | long |

##### Responses

| Code | Description |
| ---- | ----------- |
| 200 | OK |
| 400 | Bad Request |

### /api/employee/all

#### GET
##### Summary:

Get all employees

##### Description:

Retrieves all employee records in the system

##### Responses

| Code | Description |
| ---- | ----------- |
| 200 | OK |
| 400 | Bad Request |

### /api/attendance-record/employees/{employeeId}

#### GET
##### Summary:

Get attendance record of an employee

##### Description:

Retrieves the attendance record of an employee between two dates

##### Parameters

| Name | Located in | Description | Required | Schema |
| ---- | ---------- | ----------- | -------- | ---- |
| employeeId | path |  | Yes | long |
| startDate | query |  | No | date |
| endDate | query |  | No | date |

##### Responses

| Code | Description |
| ---- | ----------- |
| 200 | OK |
| 400 | Bad Request |

### /api/attendance-record/employees/{employeeId}/work-time

#### GET
##### Summary:

Get all attendance records

##### Description:

Retrieves all attendance records within a specified date range

##### Parameters

| Name | Located in | Description | Required | Schema |
| ---- | ---------- | ----------- | -------- | ---- |
| employeeId | path |  | Yes | long |
| startDate | query |  | No | date |
| endDate | query |  | No | date |

##### Responses

| Code | Description |
| ---- | ----------- |
| 200 | OK |
| 400 | Bad Request |

### /api/attendance-record/employees/{employeeId}/records/late-check-ins

#### GET
##### Summary:

Get late check-ins of an employee

##### Description:

Retrieves all attendance records with late check-ins based on the employee's attendance policy

##### Parameters

| Name | Located in | Description | Required | Schema |
| ---- | ---------- | ----------- | -------- | ---- |
| employeeId | path |  | Yes | long |

##### Responses

| Code | Description |
| ---- | ----------- |
| 200 | OK |
| 400 | Bad Request |

### /api/attendance-record/employees/{employeeId}/records/early-leaves

#### GET
##### Summary:

Get early leaves of an employee

##### Description:

Retrieves all attendance records with early leaves based on the employee's attendance policy

##### Parameters

| Name | Located in | Description | Required | Schema |
| ---- | ---------- | ----------- | -------- | ---- |
| employeeId | path |  | Yes | long |

##### Responses

| Code | Description |
| ---- | ----------- |
| 200 | OK |
| 400 | Bad Request |

### /api/attendance-record/employees/{employeeId}/records/absent-days

#### GET
##### Summary:

Get absent days of an employee

##### Description:

Retrieves all attendance records with absent days based on the employee's attendance policy

##### Parameters

| Name | Located in | Description | Required | Schema |
| ---- | ---------- | ----------- | -------- | ---- |
| employeeId | path |  | Yes | long |

##### Responses

| Code | Description |
| ---- | ----------- |
| 200 | OK |
| 400 | Bad Request |

### /api/attendance-record/all

#### GET
##### Summary:

Get all attendance records

##### Description:

Retrieves all attendance records within a specified date range

##### Parameters

| Name | Located in | Description | Required | Schema |
| ---- | ---------- | ----------- | -------- | ---- |
| startDate | query |  | No | date |
| endDate | query |  | No | date |

##### Responses

| Code | Description |
| ---- | ----------- |
| 200 | OK |
| 400 | Bad Request |

### /api/attendance-policy/all

#### GET
##### Summary:

Get all attendance policies

##### Description:

Retrieves all attendance policies in the system

##### Responses

| Code | Description |
| ---- | ----------- |
| 200 | OK |
| 400 | Bad Request |
