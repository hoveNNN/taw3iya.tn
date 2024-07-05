export interface CreateUser {
   
    firstName: string;
    lastName: string;
    email: string;
    password: string;
    confirmpassword: String,
    gender:string;
    // role: string;
  }

  export interface LoginDto{
    email: string;
    password: string;
    token?: string;
  }