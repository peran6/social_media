
# Social Media API  
By Perantonakis Giannis


## Features

- Login/Register (Authentication with JWT)
- Create posts
- Create comments
- Follow a user
- View posts by following 
- View "User" posts and comments
- View all comments on "User" posts 
- View latest comments on all posts of "User" or any follower
- Retrieve followers and following
- Retrieve not following users
- Sharable link for a post of the "User"

*"User" is the signed in user from the JWT token


## Tech Stack

**Backend:** Java 17, Spring Boot 3.3.5

**Database:** PostgreSQL

**Authentication:** Spring Security JWT

**Other Libraris:** Lombok, jjwt, JPA


##  Configuration

application.properties 

```bash
  spring.datasource.url=jdbc:postgresql://localhost:5432/social_media_db
  spring.datasource.username=db_username
  spring.datasource.password=db_password
```


## Swagger 

```bash
  http://localhost:8080/social-media-swagger-ui
```


## API Reference

- #### Register

```
    POST /api/register
    Content-Type: application/json
```
Request Body:

| Body Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `email` | `string` | **Required**. Email will be used as username |
| `password` | `string` | **Required**.  |
| `role` | `Role` | **Required**. "FREE" or "PREMIUM" |

Request Body Example:
```
{
    "email": "user9@email.com",
    "password": "123456",
    "role": "PREMIUM"
}
```
    
- #### Login

```
    POST /api/login

```

| Body Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `email` | `string` | **Required**. Email will be used as username |
| `password` | `string` | **Required**.  |

Request Body Example:
```
{
    "email": "user9@email.com",
    "password": "123456"
}
```

- #### Create Post

```
    GET /api/posts
    Authorization: Bearer token
    Content-Type: text/plain
```

Request Body Example:
```
my test Post by user9
```
- #### Create Comment

```
    POST /api/comments
    Authorization: Bearer token
    Content-Type: text/plain
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `postId` | `int` | **Required**. PostId to make the Comment |

Request Body Example:
```
my comment to post
```

- #### Make Followers

```
    POST /api/follower/follow
    Authorization: Bearer token
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `targetId` | `int` | **Required**. UserId to make follow |


- #### Delete Follower

```
    DELETE /api/follower/unfollow
    Authorization: Bearer token
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `targetId` | `int` | **Required**. UserId to make unfollow |

- #### View Feed
View all original posts by the people they follow, ordered by reverse
chronological order.

```
    GET /api/view/feed
    Authorization: Bearer token
```

- #### View myProfile
View their own posts, including the latest 100 comments, sorted by
reverse chronological order.

```
    GET /api/view/myProfile
    Authorization: Bearer token
```

- #### View comments
View all comments on their own posts.

```
    GET /api/view/comments
    Authorization: Bearer token
```

- #### View latestComments
View the latest comments on all posts they have posted or any posts by
the people they follow.

```
    GET /api/view/latestComments
    Authorization: Bearer token
```

- #### View followers
Retrieve followers and following: View the list of followers for a user.

```
    GET /api/view/followers
    Authorization: Bearer token
```

- #### View - Search Users
Search for a user to follow.

```
    GET /api/view/search
    Authorization: Bearer token
```

- #### Genearte Sharable Post
Make link for one of the post of the user

```
    GET /api/shareable-post/link/{postId}
    Authorization: Bearer token
```
| Path Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `postId` | `int` | **Required**. PostId to share |

- #### View Sharable Post
View the sharable post

```
    GET /api/shareable-post/view/{postId}
```
| Path Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `postId` | `int` | **Required**. PostId that is shared |