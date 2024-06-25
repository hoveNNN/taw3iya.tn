export class User {
    id:number;
    fName:string;
    lName:string;
    gender:string;
    email:string;
    image:string;
    constructor ( id:number,
        fName:string,
        lName:string,
        gender:string,
        email:string,
        image:string,){
            this.id=id;
            this.fName=fName;
            this.lName=lName;
            this.gender=gender
            this.email=email;
            this.image=image;
        }

}
