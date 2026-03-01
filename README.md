Available APIS
-----------------
Bulk save API

curl --location 'http://localhost:8080/api/employees/batch' \
--header 'Content-Type: application/json' \
--data-raw '[
{
"name": "Employee1",
"skills":["java","python"],
"email": "emp1@test.com",
"department": "HR",
"phone":"1234567890",
"salary": 30000,
"hireDate": "2024-06-15",
"experience":1,
"qualifications": [
{ "qualification": "B_TECH", "institution": "IIT Delhi", "year": 2018 }
]
},
{
"name": "Employee2",
"email": "emp2@test.com",
"department": "HR",
"skills":["java","python"],
"phone":"1234567890",
"hireDate": "2024-06-15",
"salary": 31000,
"experience":1,
"qualifications": [
{ "qualification": "B_TECH", "institution": "IIT Delhi", "year": 2018 }
]
},
{
"name": "Employee3",
"email": "emp3@test.com",
"department": "HR",
"phone":"1234567890",
"salary": 32000,
"hireDate": "2024-06-15",
"experience":12,
"qualifications": [
{ "qualification": "B_TECH", "institution": "IIT Delhi", "year": 2018 }
]
},
{
"name": "Employee4",
"email": "emp4@test.com",
"department": "HR",
"phone":"1234567890",
"hireDate": "2024-06-15",
"salary": 33000,
"experience":4,
"qualifications": [
{ "qualification": "B_TECH", "institution": "IIT Delhi", "year": 2018 }
]
},
{
"name": "Employee5",
"email": "emp5@test.com",
"department": "HR",
"salary": 34000,
"experience":4,
"hireDate": "2024-06-15",
"qualifications": [
{ "qualification": "B_TECH", "institution": "IIT Delhi", "year": 2018 }
]
},
{
"name": "Employee6",
"email": "emp6@test.com",
"department": "HR",
"phone":"1234567890",
"salary": 35000,
"hireDate": "2024-06-15",
"qualifications": [
{ "qualification": "B_TECH", "institution": "IIT Delhi", "year": 2018 }
]
},
{
"name": "Employee7",
"email": "emp7@test.com",
"department": "HR",
"phone":"1234567890",
"salary": 36000,
"hireDate": "2025-06-10",
"experience":4,
"qualifications": [
{ "qualification": "B_TECH", "institution": "IIT Delhi", "year": 2018 }
]
},
{
"name": "Employee8",
"email": "emp8@test.com",
"department": "HR",
"phone":"1234567890",
"salary": 37000,
"experience":4,
"hireDate": "2024-04-15",
"qualifications": [
{ "qualification": "B_TECH", "institution": "IIT Delhi", "year": 2018 }
]
},
{
"name": "Employee9",
"email": "emp9@test.com",
"department": "HR",
"phone":"1234567890",
"salary": 38000,
"hireDate": "2024-06-28",
"experience":4,
"qualifications": [
{ "qualification": "B_TECH", "institution": "IIT Delhi", "year": 2018 }
]
},
{
"name": "Employee10",
"email": "emp10@test.com",
"department": "HR",
"phone":"1234567890",
"salary": 39000,
"experience":6,
"hireDate": "2024-06-24",
"qualifications": [
{ "qualification": "B_TECH", "institution": "IIT Delhi", "year": 2018 },
{ "qualification": "MSC", "institution": "IIT Kerala", "year": 2020 },
{ "qualification": "PHD", "institution": "IIT Kanpur", "year": 2020 }
]
},

{
"name": "Employee11",
"email": "emp11@test.com",
"department": "HR",
"phone":"1234567890",
"salary": 40000,
"experience":2,
"hireDate": "2024-06-21",
"qualifications": [
{ "qualification": "B_TECH", "institution": "IIT Delhi", "year": 2018 }
]
},
{
"name": "Employee12",
"email": "emp11@test.com",
"department": "HR",
"phone":"1234567890",
"salary": 41000,
"hireDate": "2024-06-18",
"experience":2,
"qualifications": [
{ "qualification": "B_TECH", "institution": "IIT Delhi", "year": 2018 }
]
},
{
"name": "Employee13",
"email": "emp10@test.com",
"department": "HR",
"phone":"1234567890",
"salary": 42000,
"hireDate": "2024-06-15",
"experience":3,
"qualifications": [
{ "qualification": "B_TECH", "institution": "IIT Delhi", "year": 2018 }
]
},
{
"name": "Employee14",
"email": "emp14@test.com",
"department": "HR",
"phone":"1234567890",
"salary": 43000,
"hireDate": "2024-06-15",
"experience":3,
"qualifications": [
{ "qualification": "B_TECH", "institution": "IIT Delhi", "year": 2018 }
]
},
{
"name": "Employee15",
"email": "emp15@test.com",
"department": "HR",
"phone":"1234567890",
"salary": 44000,
"hireDate": "2024-06-15",
"experience":5,
"qualifications": [
{ "qualification": "B_TECH", "institution": "IIT Delhi", "year": 2018 }
]
},
{
"name": "Employee16",
"email": "emp16@test.com",
"department": "HR",
"phone":"1234567890",
"salary": 45000,
"hireDate": "2024-06-15",
"experience":2,
"qualifications": [
{ "qualification": "B_TECH", "institution": "IIT Delhi", "year": 2018 }
]
},
{
"name": "Employee17",
"email": "emp17@test.com",
"department": "HR",
"phone":"1234567890",
"salary": 46000,
"hireDate": "2024-06-15",
"experience":7,
"qualifications": [
{ "qualification": "B_TECH", "institution": "IIT Delhi", "year": 2018 }
]
},
{
"name": "Employee18",
"email": "emp18@test.com",
"department": "HR",
"phone":"1234567890",
"salary": 47000,
"hireDate": "2024-06-15",
"experience":4,
"qualifications": [
{ "qualification": "B_TECH", "institution": "IIT Delhi", "year": 2018 }
]
},
{
"name": "Employee19",
"email": "emp19@test.com",
"department": "HR",
"phone":"1234567890",
"salary": 48000,
"hireDate": "2024-06-15",
"experience":6,
"qualifications": [
{ "qualification": "B_TECH", "institution": "IIT Delhi", "year": 2018 }
]
},
{
"name": "Employee20",
"email": "emp20@test.com",
"department": "HR",
"phone":"1234567890",
"salary": 49000,
"hireDate": "2024-06-15",
"experience":8,
"qualifications": [
{ "qualification": "B_TECH", "institution": "IIT Delhi", "year": 2018 }
]
},
{
"name": "Employee21",
"email": "emp21@test.com",
"department": "HR",
"phone":"1234567890",
"salary": 50000,
"hireDate": "2024-06-15",
"experience":9,
"qualifications": [
{ "qualification": "B_TECH", "institution": "IIT Delhi", "year": 2018 }
]
},
{
"name": "Employee22",
"email": "emp22@test.com",
"department": "HR",
"phone":"1234567890",
"salary": 51000,
"hireDate": "2024-06-15",
"experience":10,
"qualifications": [
{ "qualification": "B_TECH", "institution": "IIT Delhi", "year": 2018 }
]
},
{
"name": "Employee23",
"email": "emp23@test.com",
"department": "HR",
"phone":"1234567890",
"salary": 52000,
"hireDate": "2024-06-15",
"experience":11,
"qualifications": [
{ "qualification": "B_TECH", "institution": "IIT Delhi", "year": 2018 }
]
},
{
"name": "Employee24",
"email": "emp24@test.com",
"department": "HR",
"phone":"1234567890",
"salary": 53000,
"hireDate": "2024-06-15",
"experience":12,
"qualifications": [
{ "qualification": "B_TECH", "institution": "IIT Delhi", "year": 2018 }
]
},
{
"name": "Employee25",
"email": "emp25@test.com",
"department": "HR",
"phone":"1234567890",
"salary": 54000,
"hireDate": "2024-06-15",
"experience":13,
"qualifications": [
{ "qualification": "B_TECH", "institution": "IIT Delhi", "year": 2018 }
]
},
{
"name": "Employee26",
"email": "emp26@test.com",
"department": "HR",
"phone":"1234567890",
"salary": 55000,
"hireDate": "2024-06-15",
"experience":14,
"qualifications": [
{ "qualification": "B_TECH", "institution": "IIT Delhi", "year": 2018 }
]
},
{
"name": "Employee27",
"email": "emp27@test.com",
"department": "HR",
"phone":"1234567890",
"salary": 56000,
"hireDate": "2024-06-15",
"experience":15,
"qualifications": [
{ "qualification": "B_TECH", "institution": "IIT Delhi", "year": 2018 }
]
},
{
"name": "Employee28",
"email": "emp28@test.com",
"department": "HR",
"phone":"1234567890",
"salary": 57000,
"hireDate": "2024-06-15",
"experience":16,
"qualifications": [
{ "qualification": "B_TECH", "institution": "IIT Delhi", "year": 2018 }
]
},
{
"name": "Employee29",
"email": "emp29@test.com",
"department": "HR",
"phone":"1234567890",
"salary": 58000,
"hireDate": "2024-06-15",
"experience":17,
"qualifications": [
{ "qualification": "B_TECH", "institution": "IIT Delhi", "year": 2018 }
]
},
{
"name": "Employee30",
"email": "emp30@test.com",
"department": "HR",
"phone":"1234567890",
"salary": 59000,
"hireDate": "2024-06-15",
"experience":18,
"qualifications": [
{ "qualification": "B_TECH", "institution": "IIT Delhi", "year": 2018 }
]
},
{
"name": "Employee31",
"email": "emp31@test.com",
"department": "HR",
"phone":"1234567890",
"salary": 60000,
"hireDate": "2024-06-15",
"experience":19,
"qualifications": [
{ "qualification": "B_TECH", "institution": "IIT Delhi", "year": 2018 }
]
},
{
"name": "Employee32",
"email": "emp32@test.com",
"department": "HR",
"phone":"1234567890",
"salary": 61000,
"hireDate": "2024-06-15",
"experience":20,
"qualifications": [
{ "qualification": "B_TECH", "institution": "IIT Delhi", "year": 2018 }
]
},
{
"name": "Employee33",
"email": "emp33@test.com",
"department": "HR",
"phone":"1234567890",
"salary": 62000,
"hireDate": "2024-06-15",
"experience":21,
"qualifications": [
{ "qualification": "B_TECH", "institution": "IIT Delhi", "year": 2018 }
]
}
]'

2. Paginated GET All API with filter

curl --location 'http://localhost:8080/api/employees?experience=1'

3. Delete by Id API

curl --location --request DELETE 'http://localhost:8080/api/employees/13'

4. PUT API

curl --location --request PUT 'http://localhost:8080/api/employees/16' \
--header 'Cookie: JSESSIONID=B42CD960E29E20014FCA4A228C057E3A' \
--header 'Content-Type: application/json' \
--data-raw '{
"id":16,
"name": "Employee4a",
"email": "emp4a@test.com",
"department": "IT",
"phone":"1234567891",
"hireDate": "2026-06-15",
"salary": 33001,
"experience":40,
"qualifications": [
{ "qualification": "MSC", "institution": "IIT Delhi", "year": 2018 }
]
}'
