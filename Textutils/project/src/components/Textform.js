import React from "react";
import { useState } from "react";
import PropTypes from "prop-types";


const TextForm = (props) => {
const [text, setText] = useState("");

const handleClick = () => {
    let newText = text.toUpperCase();
    setText(newText);
    props.showAlert("Converted to uppercase!", "success")
};
const handleLoClick = () => {
    let newText = text.toLowerCase();
    setText(newText);
    props.showAlert("Converted to lowercase!", "success")
};
const handleClearClick = () => {
    setText("");
    props.showAlert("Text cleared!", "success")
};
const handleOnChange = (e) => {
    setText(e.target.value);
};
const handleCopy = () => {
    let text = document.getElementById("myBox")
    text.select()
    navigator.clipboard.writeText(text.value)
    props.showAlert("Text copied to clipboard!", "success")
};

const removeExtraSpaces = () => {
    let newText = text.split(/[ ] + /)
    setText(newText.join(" "))
    props.showAlert("Removed extra spaces!", "success")
}


return (
    <>
    <h2 style= {{color: props.mode === "dark" ? "white" : "#042743"}}>{props.heading}</h2>
    <div style= {{color: props.mode === "dark" ? "white" : "#042743"}}>
    <div className="mb-3 container">
        <textarea
        className="form-control"
        value={text}
        style={{
            backgroundColor: props.mode === "dark" ? "grey" : "white",
            color: props.mode === "dark" ? "white" : "#042743",
        }}
        onChange={handleOnChange}
        id="myBox"
        rows={8}
        />
    </div>
    <button className="btn btn-primary mx-1" onClick={handleClick}>
        Convert to UpperCase
    </button>
    <button className="btn btn-primary mx-1" onClick={handleLoClick}>
        Convert to LowerCase
    </button>
    <button className="btn btn-primary mx-1" onClick={handleClearClick}>
        Clear Text
    </button>
    <button className="btn btn-primary mx-1" onClick={handleCopy}>
        Copy Text
    </button>
    <button className="btn btn-primary mx-1" onClick={removeExtraSpaces}>
        Remove Extra Spaces
    </button>
    </div>

    <div className="container my-3" style= {{color: props.mode === "dark" ? "white" : "#042743"}}>
    <h1>Your text Summary</h1>
    <p className="">
        {text.split(" ").length} words and {text.length} characters
    </p>
    <p>{0.08 * text.split(" ").length} Minutes to Read</p>
    <h2>Preview</h2>
    <p>{text.length>0 ? text :"Enter something in the textbox to preview it"}</p>
    </div>
    </>
    );
};

export default TextForm;

TextForm.propTypes = {
    heading: PropTypes.string,
};

// TextForm.defaultProps = {
//     heading: "you have to set heading"
// }
