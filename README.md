## Get All Posts
GET http://localhost:8080/api/v1/posts

## Filter by author
GET http://localhost:8080/api/v1/posts?author=Someone

## Filter by author containing keywords
GET http://localhost:8080/api/v1/posts?author:containing=So

## Get One Post
GET http://localhost:8080/api/v1/posts/{id}

## Create new Post
POST http://localhost:8080/api/v1/posts
```
{
  "title": "Some title",
  "content": "Some contents"
  "author": "Someone"
}
```

## Update existing Post
POST http://localhost:8080/api/v1/posts/{id}
```
{
  "title": "Updated title",
  "content": "Updated contents"
  "author": "Someone New"
}
```

## Delete Post
DELETE http://localhost:8080/api/v1/post/{id}
