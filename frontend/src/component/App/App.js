import './App.css';
import React, {Component} from "react";
import {BrowserRouter as Router, Navigate, Route, Routes} from 'react-router-dom'
import Header from "../Header/header";
import Categories from "../Categories/categories";
import ELibraryService from "../../repository/booksRepository";
import BookAdd from "../Books/BookAdd/bookAdd";
import BookEdit from "../Books/BookEdit/bookEdit";
import Author from "../Authors/author";
import Books from "../Books/BookList/books";


class App extends Component{
  constructor(props) {
    super(props);
    this.state = {
        books: [],
        authors: [],
        categories: [],
        countries: [],
        selectedBook: {}
    }
  }

    render() {
        return (
            <Router>
                <main>
                    <Header/>
                    <div className={"container"}>
                        <Routes>
                            <Route path={"/categories"}
                                   element={<Categories categories={this.state.categories}/>} exact/>
                            <Route path={"/books/add"}
                                   element={<BookAdd categories={this.state.categories}
                                                     authors={this.state.authors}
                                                     onAddBook={this.addBook}/>} exact/>
                            <Route path={"/books/edit/:id"}
                                   element={<BookEdit categories={this.state.categories}
                                                      authors={this.state.authors}
                                                      onEditBook={this.editBook}
                                                      book={this.state.selectedBook}
                                   />} exact/>
                            <Route path={"/books"}
                                   element={<Books books={this.state.books}
                                                   onDelete={this.deleteBook}
                                                   onEdit={this.getBook}
                                                   onMarkAsTaken={this.markBookAsTaken}
                                   />}
                                   exact/>
                            <Route path="/" element={<Navigate replace to="/books"/>}/>
                        </Routes>
                    </div>
                </main>
            </Router>
        );
    }

  componentDidMount() {
    this.loadBooks();
    this.loadCategories();
    this.loadAuthors();
    this.loadCountries();
  }

  loadBooks = () => {
      ELibraryService.fetchBooks()
        .then((data) => {
          this.setState({
            books: data.data
          })
        });
  }

  loadAuthors = () => {
      ELibraryService.fetchAuthors()
        .then((data) => {
          this.setState({
            authors: data.data
          })
        });
  }

  loadCategories = () => {
      ELibraryService.fetchCategories()
        .then((data) => {
          this.setState({
            categories: data.data
          })
        });
  }

  loadCountries = () => {
      ELibraryService.fetchCountries()
        .then((data) => {
          this.setState({
            countries: data.data
          })
        });
  }

  deleteBook = (id) => {
      ELibraryService.deleteBook(id)
        .then(() => {
          this.loadBooks();
        });
  }

  addBook = (name, category, authorId, availableCopies) => {
      ELibraryService.addBook(name, category, authorId, availableCopies)
        .then(() => {
          this.loadBooks();
        });
  }

  getBook = (id) => {
      ELibraryService.getBook(id)
        .then((data) => {
          this.setState({
            selectedBook: data.data
          })
        })
  }

    editBook = (id, name, category, authorId, availableCopies) => {
        ELibraryService.editBook(id, name, category, authorId, availableCopies)
            .then(() => {
                this.loadBooks();
            });
    }

    markBookAsTaken = (id) => {
        ELibraryService.markBookAsTaken(id)
            .then(() => {
                this.loadBooks();
            });
    }


}

export default App;
