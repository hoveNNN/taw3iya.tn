export class User {
    id:number;
    fName:string;
    lName:string;
    gender:string;
    role:string;
    email:string;
    image:string;
    constructor ( id:number,
        fName:string,
        lName:string,
        gender:string,
        email:string,
        role:string,
        image:string,){
            this.id=id;
            this.fName=fName;
            this.lName=lName;
            this.gender=gender
            this.email=email;
            this.role=role;
            this.image=image;
        }

}
