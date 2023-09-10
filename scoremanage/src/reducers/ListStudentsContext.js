import React, { createContext, useContext, useState } from 'react';

// Tạo một Context
const ListStudentsContext = createContext();

// Tạo một Provider cho Context
export const ListStudentsProvider = ({ children }) => {
    const [liststudents, setListStudents] = useState([]);

    return (
        <ListStudentsContext.Provider value={{ liststudents, setListStudents }}>
            {children}
        </ListStudentsContext.Provider>
    );
};

// Tạo một custom hook để sử dụng giá trị của Context
export const useListStudents = () => {
    return useContext(ListStudentsContext);
};
