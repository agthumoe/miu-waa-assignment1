# Assignment 1
## Get All Posts
```GET http://localhost:8080/api/v1/posts```

## Filter by author
```GET http://localhost:8080/api/v1/posts?author=Someone```

## Filter by author containing keywords
```GET http://localhost:8080/api/v1/posts?author:containing=So```

## Get One Post
```GET http://localhost:8080/api/v1/posts/{id}```

## Create new Post
```POST http://localhost:8080/api/v1/posts```
```json
{
  "title": "Some title",
  "content": "Some contents"
  "author": "Someone"
}
```

## Update existing Post
```POST http://localhost:8080/api/v1/posts/{id}```
```json
{
  "title": "Updated title",
  "content": "Updated contents"
  "author": "Someone New"
}
```

## Delete Post
```DELETE http://localhost:8080/api/v1/post/{id}```

# Assignment 2

## Get Users
This should retrieve all the users in the database.

```GET	localhost:8080/users```

## Get One User
This should retrieve the user with id = 1.

```GET	localhost:8080/users/1```

## Create New User
This should create and save a new user.

```POST	localhost:8080/users```
```json
{
  "name": "New User"
}
```
## Get Posts by User
This should retrieve the posts of the user where id = 1.

```GET	localhost:8080/users/1/posts```

## Make a query that will return all the users that have more than 1 post
```GET localhost:8080/api/v1/users?posts:gt=1```