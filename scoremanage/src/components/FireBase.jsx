import { useState } from "react";
import Login from "./LoginFireBase";
import Chat from "./ChatFireBase";

function FireBase() {
    const [showChat, setShowChat] = useState(false);
    const [name, setName] = useState("");
    const getName = (name) => {
        setName(name);
        setShowChat(true);
    };
    return (
        <div className="App">
            {!showChat && <Login callback={getName} />}
            {showChat && <Chat name={name} />}
        </div>
    );
}

export default FireBase;