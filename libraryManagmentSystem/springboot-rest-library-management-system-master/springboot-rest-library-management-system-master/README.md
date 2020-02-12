# springboot-rest-library-management-system
A complete Spring Boot example application for REST APIs; its purpose is to demonstrate various API .

	All List of Library's users
	GET
	http://localhost:9080/api/users
	
	API Endpoints api/users
	
	Response
	
	[
	    {
	        "userid": "111-1",
	        "name": "amit",
	        "address": "Nainital",
	        "book1issue": "",
	        "book2issue": "",
	        "book1return": "",
	        "book2return": "",
	        "_links": {
	            "self": {
	                "href": "http://localhost:9080/api/user/111-1"
	            }
	        }
	    },
	    {
	        "userid": "111-2",
	        "name": "Harish",
	        "address": "Lucknow",
	        "book1issue": "",
	        "book2issue": "",
	        "book1return": "",
	        "book2return": "",
	        "_links": {
	            "self": {
	                "href": "http://localhost:9080/api/user/111-2"
	            }
	        }
	    }
	]
	
	
	All List of book in Library
	
	GET
	api/books
	http://localhost:9080/api/books
	
	Response
	
	[
	    {
	        "isbn": "111-1",
	        "title": "autobiography of a yogi",
	        "author": "Yogananda",
	        "publisher": "pbs publisher",
	        "status": "Avilable",
	        "borrower": "none",
	        "borrowDate": "none",
	        "returnDate": "none",
	        "_links": {
	            "self": {
	                "href": "http://localhost:9080/api/book/111-1"
	            }
	        }
	    },
	    {
	        "isbn": "111-2",
	        "title": "Gita",
	        "author": "Lord Krishna",
	        "publisher": "pbs publisher",
	        "status": "Avilable",
	        "borrower": "none",
	        "borrowDate": "none",
	        "returnDate": "none",
	        "_links": {
	            "self": {
	                "href": "http://localhost:9080/api/book/111-2"
	            }
	        }
	    }
	]







1) Ability to add books to the system.

	POST
	api/books
	
	http://localhost:9080/api/book
	
	Request Pay load or body
	
	{
		"isbn": "111-4",
	 	"title": ".net",
	 	"author": "microsoft", 	
	 	"publisher": "PBS",
	 	"status": "Avilable",
	 	"borrower": "none",
	 	"borrowDate": "none",
	 	"returnDate": "none"
	        
	}
	
	Response 
	
	{
	    "isbn": "111-4",
	    "title": ".net",
	    "author": "microsoft",
	    "_links": {
	        "self": {
	            "href": "http://localhost:9080/api/book/111-4"
	        },
	        "Books": {
	            "href": "http://localhost:9080/api/books"
	        }
	    }
	}






2) Ability to add users to the system.

	POST
	api/users`
	
	
	
	http://localhost:9080/api/user
	
	Request Pay load
	
	{
		"userid": "111-3",
		"name": "yogesh mungali",
	    "address": "noida, india, pin 202020",
	    "book1issue": "",
	    "book2issue": "",
	    "book1return": "",
	    "book2return": ""
	    
	}
	
	Response
	
	{
	    "userid": "111-3",
	    "name": "yogesh mungali",
	    "address": "noida, india, pin 202020",
	    "book1issue": null,
	    "book2issue": null,
	    "book1return": null,
	    "book2return": null,
	    "_links": {
	        "self": {
	            "href": "http://localhost:9080/api/user/111-3"
	        },
	        "Users": {
	            "href": "http://localhost:9080/api/users"
	        }
	     }
	}

3) Ability to lend books to users.

	GET
	API End point lendbook/{user id}/{book id}
	request example 
	
	Get Method and link 
	
	http://localhost:9080/api/111-1/111-1
	
	Response
	{
	    "userid": "111-1",
	    "name": "amit",
	    "address": "Nainital",
	    "book1issue": "Book 1 Issue :autobiography of a yogi",
	    "book2issue": null,
	    "book1return": null,
	    "book2return": null
	}


4) Ability to return books to the library.

	GET
	API End point return/{user id}/{book id}
	
	request example 
	
	Get Method and link 
	
	http://localhost:9080/api/return/111-1/111-1
	
	Response
	
	[
	    {
	        "isbn": "111-1",
	        "title": "autobiography of a yogi",
	        "author": "Yogananda",
	        "publisher": "pbs publisher",
	        "status": "Avilable",
	        "borrower": "none",
	        "borrowDate": "none",
	        "returnDate": "none",
	        "_links": {
	            "self": {
	                "href": "http://localhost:9080/api/book/111-1"
	            }
	        }
	    },
	    {
	        "isbn": "111-2",
	        "title": "Gita",
	        "author": "Lord Krishna",
	        "publisher": "pbs publisher",
	        "status": "Avilable",
	        "borrower": "none",
	        "borrowDate": "none",
	        "returnDate": "none",
	        "_links": {
	            "self": {
	                "href": "http://localhost:9080/api/book/111-2"
	            }
	        }
	    }
	]




5) Ability to limit the number of books borrowed by user.

	"Only max 2  books can borrowed by per user";
	if he try more then 2 books will get user.status = "You [user id ]"+userid +"have used total limit as only max 2  books can borrowed 	by per user"


6) Ability to search a book by title, author.

	search a book by title
	API End point api/search/book/{title}
	
	request example (search a book by title("Gita"))

	Get Method and link 	
	http://localhost:9080/api/search/book/Gita
	
	Response
	[
    {
        "isbn": "111-4",
        "title": "Gita",
        "author": "Iskon",
        "publisher": "pbs publisher",
        "status": "Avilable",
        "borrower": "none",
        "borrowDate": "none",
        "returnDate": "none"
    },
    {
        "isbn": "111-2",
        "title": "Gita",
        "author": "Lord Krishna",
        "publisher": "Kbs publisher",
        "status": "Avilable",
        "borrower": "none",
        "borrowDate": "none",
        "returnDate": "none"
    }
]
	


	search a book by author
	API End point api/search/book/{author}
	
	request example (search a book by author("Iskon"))

	Get Method and link 	
	http://localhost:9080/api/search/book/Iskon
	
	Response
	[
    {
        "isbn": "111-5",
        "title": "shreemad bhagwat geeta",
        "author": "Iskon",
        "publisher": "pbs publisher",
        "status": "Avilable",
        "borrower": "none",
        "borrowDate": "none",
        "returnDate": "none"
    },
    {
        "isbn": "111-4",
        "title": "Gita",
        "author": "Iskon",
        "publisher": "pbs publisher",
        "status": "Avilable",
        "borrower": "none",
        "borrowDate": "none",
        "returnDate": "none"
    }
]



7) Ability to search a user by name.

I have added 2 user with same name and name is  "Amit"


GET
API End point api/search/user/{name}

request example 

Get Method and link  

http://localhost:9080/api/search/user/amit

Response
[
    {
        "userid": "111-2",
        "name": "amit",
        "address": "Delhi",
        "book1issue": "none",
        "book2issue": "none",
        "book1return": "none",
        "book2return": "none",
        "status": "none",
        "_links": {
            "self": {
                "href": "http://localhost:9080/api/user/amit"
            },
            "Users": {
                "href": "http://localhost:9080/api/users"
            }
        }
    },
    {
        "userid": "111-2",
        "name": "amit",
        "address": "Delhi",
        "book1issue": "none",
        "book2issue": "none",
        "book1return": "none",
        "book2return": "none",
        "status": "none",
        "_links": {
            "self": {
                "href": "http://localhost:9080/api/user/amit"
            },
            "Users": {
                "href": "http://localhost:9080/api/users"
            }
        }
    }
]

##Tools
	*STS
	*SpringBoot

## Installation
* Ensure that Java 8 and Maven 3.2 are installed
* Some of the documentation tools also required Node to be installed
* Clone this repo:
    https://gitlab.com/mungali/springboot-rest-library-management-system

## Usage
### Running the Spring Boot app
Navigate to the directory into which you cloned the repo and execute this:
`mvn spring-boot:run`

Once started you can access the APIs on port 9080, e.g.
`http://localhost:9080/api/books`

The port number can be changed by editing the port property in `src/main/resources/application.yml`

Requirements given below.


Implement a Library Management System (in the programming language of your choice) that provides the (tasks)
1) Ability to add books to the system.
2) Ability to add users to the system.
3) Ability to lend books to users.
4) Ability to return books to the library.
5) Ability to limit the number of books borrowed by user.
6) Ability to search a book by title, author.
7) Ability to search a user by name.
Guidelines :
1) Please implement the functionalities in the order as mentioned above.
2) You do not need to implement any persistence mechanism - a database or files. Please use the Java’s basic data structures like List, Map etc.
3) Please write a production quality code. Ex: Even though the current requirement is to store everything in-memory, we should have the ability to replace it with a persistence layer, say, MySQL.
4) Please zip your code and mail to madhu.b@cleartrip.com.
Evaluation Criteria (In the order of priority)
1) Correctness - The code should compile and execute correctly. It should not break, say, for invalid inputs. If you have shortage of time, focus on implementing, say, first 4 tasks correctly rather than implementing all the 7 tasks incorrectly.
2) DRY - Wherever applicable follow DRY - Don’t Repeat Yourself.
3) Completeness - Implementing all the 7 tasks, of course correctly, is the third priority.
4) Code quality - All the aspects of code quality. Ex: OOPS, Naming Conventions etc.
 