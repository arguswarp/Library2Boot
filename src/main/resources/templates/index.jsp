<html>
<body>
<h2>Web Library</h2>
<p><a href="/people">To people</a></p>
<p><a href="/books">To books</a></p>
<form method="get" action="/books">
    <label for="page">Page</label>
    <input style="width: 30px" type="text" name="page" id="page" value="0"/>
    <label for="booksPerPage">Books per page</label>
    <input style="width: 30px" type="text" name="books_per_page" id="booksPerPage"/>
    <label for="sort">Enable sort</label>
    <select id="sort" name="sort_by_year">
        <option value="true">True</option>
        <option value="false" selected>False</option>
    </select>
    <input type="submit" value="Show books">
</form>
<a href="/books/find">Find book</a>
</body>
</html>
