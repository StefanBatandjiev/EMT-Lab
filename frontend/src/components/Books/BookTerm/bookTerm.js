import React from "react";
import {Link} from "react-router-dom"

const bookTerm = (props) => {
    return (
        <tr>
            <td>{props.term.name}</td>
            <td>{props.term.category}</td>
            <td>{props.term.author.name + " " + props.term.author.surname}</td>
            <td>{props.term.availableCopies}</td>
            <td className={"text-right"}>
                <a title={"Delete"} className={"btn btn-outline-danger"}
                    onClick={() => props.onDelete(props.term.id)}
                >Delete
                </a>
                <Link className={"btn btn-outline-warning mx-2"}
                      onClick={() => props.onEdit(props.term.id)}
                      to={`/books/edit/${props.term.id}`}>
                    Edit
                </Link>
                <button title={"Mark"} className="btn btn-outline-success"
                   onClick={() => props.onMark(props.term.id)}
                   disabled={props.term.availableCopies <= 0}>
                    Mark as Taken
                </button>
            </td>
        </tr>
    )
}

export default bookTerm;