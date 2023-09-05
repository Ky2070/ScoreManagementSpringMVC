import React, { createContext, useContext, useState } from 'react';

// Tạo một Context mới
const SchoolYearContext = createContext();

// Hàm con Provider để cung cấp giá trị Context cho toàn bộ ứng dụng
export const SchoolYearProvider = ({ children }) => {
    const [selectedSchoolYearId, setSelectedSchoolYearId] = useState(null);

    return (
        <SchoolYearContext.Provider value={{ selectedSchoolYearId, setSelectedSchoolYearId }}>
            {children}
        </SchoolYearContext.Provider>
    );
};

// Tạo một tùy chỉnh hook để dễ dàng truy cập giá trị Context
export const useSchoolYear = () => {
    const context = useContext(SchoolYearContext);
    if (!context) {
        throw new Error('useSchoolYear must be used within a SchoolYearProvider');
    }
    return context;
};