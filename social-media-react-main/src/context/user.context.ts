import React from 'react';

export interface User {
    id: number,
    email: string,
    firstName: string,
    lastName: string
}

interface UserContextState {
    user: User | undefined;
    setUser: (user?: User) => void;
}

// Define the User Context
// This will provided at the top level of the component hierarchy
// Then any child component will be able to access the User info
// by using the useContext hook as follows:
// const { user, setUser } = useContext(UserContext);
// And then the user can be used and updated in a standard fashion
export const UserContext = React.createContext<UserContextState>({
    user: undefined,
    setUser: () => { }
});