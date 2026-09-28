request: http://localhost:8080/route/book/21

response:

{
"endpoint url ": "/route/book/21",
"message": "Book not found with bookId : 21"
}

By using exception handler, developer able to identify the mistake and able
to fix the code by looking into error messages.

How Spring get the exception and display the message on console.

Spring Reactive internally use a class DefaultErrorWebExceptionHandler to handle exceptions and
DefaultErrorWebExceptionHandler extends AbstractErrorWebExceptionHandler.

How to create own exception handler class using Spring Reactive

1. we need to create our own exception handler by extending AbstractErrorWebExceptionHandler.
2. override the getRoutingFunction


private Mono<ServerResponse> renderException(ServerRequest request) {
Map<String, Object> error = this.getErrorAttributes(request, ErrorAttributeOptions.defaults());
/*To remove specific attributes*/
// error.remove("status");
// error.remove("requestId");
return ServerResponse.status(HttpStatus.BAD_REQUEST).contentType(MediaType.APPLICATION_JSON)
.body(BodyInserters.fromValue(error));

    }



/* handled BookAPIException using spring reactive approach*/
public Mono<ServerResponse> getBookById(ServerRequest request) {
int id = Integer.parseInt(request.pathVariable("bookId"));
Mono<Book> bookMono = bookRepository.getBooks()
.filter(book -> book.getBookId() == id)
.next().switchIfEmpty(Mono.error(new BookAPIException("Book not found with bookId : "+id)));
return ServerResponse.ok().body(bookMono, Book.class);
}


response:

These below attributes are custom
{
"endpoint url ": "/route/book/21",
"message": "Book not found with bookId : 21"
}