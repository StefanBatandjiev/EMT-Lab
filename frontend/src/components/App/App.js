import './App.css';
import React, {Component} from "react";
import {BrowserRouter as Router, Route, Routes} from "react-router-dom";
import Authors from "../Authors/authors";
import Categories from "../Categories/categories"
import Books from "../Books/BookList/books"
import Header from "../Header/header"
import BookAdd from "../Books/BookAdd/bookAdd"
import BookEdit from "../Books/BookEdit/bookEdit"
import LabService from "../../repository/labRepository";

class App extends Component {

    constructor(props) {
        super(props);
        this.state = {
            authors: [],
            books: [],
            categories: [],
            selectedBook: {}
        }
    }

    render() {
        return (
            <Router>
                <Header/>
                <main>
                    <div className={"container"}>
                        <Routes>
                            <Route path={"/authors"} element ={
                                <Authors authors={this.state.authors}/>}/>
                            <Route path={"/books/add"} element = {
                                <BookAdd categories={this.state.categories}
                                         authors={this.state.authors}
                                         onAddBook={this.addBook}/>}/>
                            <Route path={"/books/edit/:id"} element = {
                                <BookEdit categories={this.state.categories}
                                          authors={this.state.authors}
                                          onEditBook={this.editBook}
                                          book={this.state.selectedBook}/>}/>
                            <Route path={"/books"} element = {
                                <Books books={this.state.books}
                                       onDelete={this.deleteBook}
                                       onEdit={this.getBook}
                                       onMark={this.markAsTaken}/>}/>
                            <Route path={"/categories"} element={
                                <Categories categories={this.state.categories}/>}/>
                        </Routes>
                    </div>
                </main>
            </Router>
        );
    }

    componentDidMount() {
        this.fetchData()
    }

    fetchData = () => {
        this.loadAuthors();
        this.loadBooks();
        this.loadCategories();
    }

    loadAuthors = () => {
        LabService.fetchAuthors()
            .then((data) => {
                this.setState({
                    authors : data.data
                })
            });
    }

    loadBooks = () => {
        LabService.fetchBooks()
            .then((data) => {
                this.setState({
                    books : data.data
                })
            });
    }

    loadCategories = () => {
        LabService.fetchCategories()
            .then((data) => {
                this.setState({
                    categories : data.data
                })
            });
    }

    deleteBook = (id) => {
        LabService.deleteBook(id)
            .then(() => {
                this.loadBooks();
            });
    }

    addBook = (name, category, author, availableCopies) => {
        LabService.addBook(name, category, author, availableCopies)
            .then(() => {
                this.loadBooks();
            });
    }

    getBook = (id) => {
        if (!id) {
            console.error('Invalid id:', id);
            return;
        }

        LabService.getBook(id)
            .then((data) => {
                this.setState({
                    selectedBook: data.data
                });
            })
            .catch((error) => {
                console.error('Error getting book:', error);
            });
        };

    editBook = (id, name, category, author, availableCopies) => {
        LabService.editBook(id, name, category, author, availableCopies)
            .then(() => {
                this.loadBooks();
            });
        }

    markAsTaken = (id) => {
        LabService.getBook(id)
            .then((response) => {
                const book = response.data;

                book.availableCopies -= 1;

                LabService.markAsTaken(id)
                    .then(() => {
                        this.loadBooks();
                    });
            });
        }
}

export default App;
