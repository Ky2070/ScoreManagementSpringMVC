// import { useState, useEffect, useRef } from "react";
// import { database, ref, push, onValue } from "../firebase";
// import "../css/FireBase.css"
// function Chat({ name }) {
//   const [inpMessage, setIptMessage] = useState("");
//   const [messages, setMessages] = useState([]);
//   const input = useRef();
//   useEffect(() => {
//     onValue(ref(database, "message"), (data) => {
//       let getMsg = [];
//       data.forEach((d) => {
//         getMsg.push(d.val());
//       });
//       setMessages(getMsg);
//     });
//   }, []);
//   const handleSendMessage = () => {
//     push(ref(database, "message"), {
//       name: name,
//       message: inpMessage,
//     });
//     setIptMessage("");
//     // input.current.focus();
//   };
//   return (
//     // <div>
//     //   <h1>xin chào {name}</h1>
//     //   <ul>
//     //     {messages.map((msg, index) => {
//     //       return (
//     //         <li key={index}>
//     //           <span>{msg.name}: </span>
//     //           <span>{msg.message}</span>
//     //         </li>
//     //       );
//     //     })}
//     //   </ul>
//     //   <input
//     //     type="text"
//     //     value={inpMessage}
//     //     onChange={(e) => {
//     //       setIptMessage(e.target.value);
//     //     }}
//     //     ref={input}
//     //   />
//     //   <button onClick={handleSendMessage}>Gửi</button>
//     // </div>
//     <div className="chat-container">
//       <h1>xin chào {name}</h1>
//       <ul className="message-list">
//         {messages.map((msg, index) => (
//           <li key={index} className="message">
//             <span className="message-name">{msg.name}:</span>
//             <span className="message-content">{msg.message}</span>
//           </li>
//         ))}
//       </ul>
//       <div className="input-container">
//         <input
//           type="text"
//           value={inpMessage}
//           onChange={(e) => {
//             setIptMessage(e.target.value);
//           }}
//           ref={input}
//           className="message-input"
//         />
//         <button onClick={handleSendMessage} className="send-button">
//           Gửi
//         </button>
//       </div>
//     </div>
//   );
// }

// export default Chat;
import { useState, useEffect, useRef } from "react";
import { database, ref, push, onValue } from "../firebase";
import "../css/FireBase.css";

function Chat({ name }) {
  const [inpMessage, setIptMessage] = useState("");
  const [messages, setMessages] = useState([]);
  const input = useRef();
  
  useEffect(() => {
    onValue(ref(database, "message"), (data) => {
      let getMsg = [];
      data.forEach((d) => {
        getMsg.push(d.val());
      });
      setMessages(getMsg);
    });
  }, []);
  
  const handleSendMessage = () => {
    push(ref(database, "message"), {
      name: name,
      message: inpMessage,
    });
    setIptMessage("");
  };

  return (
    <div className="chat-container">
      <ul className="message-list">
        {messages.map((msg, index) => (
          <li key={index} className={`message ${msg.name === name ? "right" : "left"}`}>
            <span className="message-name">{msg.name}: </span>
            <span className="message-content">{msg.message}</span>
          </li>
        ))}
      </ul>
      <div className="input-container">
        <input
          type="text"
          value={inpMessage}
          onChange={(e) => {
            setIptMessage(e.target.value);
          }}
          ref={input}
          className="message-input"
        />
        <button onClick={handleSendMessage} className="send-button">
          Gửi
        </button>
      </div>
    </div>
  );
}

export default Chat;