import {MyUserModel} from "../my-user-model/my-user-model";

export class MyKweetModel {
    constructor(public id: number,
                public text: string,
                public date: Date,
                public eigenaar: MyUserModel){}
}
