import { createContext, useContext, useState } from 'react';

// Tạo Context
export const UniqueSubjectTeacherIdContext = createContext();

// Tạo một Provider cho Context
export const UniqueSubjectTeacherIdProvider = ({ children }) => {
    const [selectedSubjectTeacherId, setSelectedSubjectTeacherId] = useState(null);

    return (
        <UniqueSubjectTeacherIdContext.Provider value={{ selectedSubjectTeacherId, setSelectedSubjectTeacherId }}>
            {children}
        </UniqueSubjectTeacherIdContext.Provider>
    );
};