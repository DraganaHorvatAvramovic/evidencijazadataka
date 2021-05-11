import React from 'react';
import EvidencijaAxios from './../../apis/EvidencijaAxios';

class AddZadaci extends React.Component{

    constructor(props){
        super(props);

        let zadatak = {
            ime: "",
            zaduzeni: "",
            bodovi: 0,
            sprint: null,
            stanje: null
        }
        this.state = {zadatak: zadatak, sprintovi: [], stanja: []}
    }

    componentDidMount(){
        this.getSprintovi();
        this.getStanja();
    }

    valueInputChanged(e) {
        let input = e.target;
    
        let name = input.name;
        let value = input.value;
    
        let zadatak = this.state.zadatak;
        zadatak[name] = value;
    
        this.setState({ zadatak: zadatak });
    }

    stanjeSelectionChanged(e){

        let stanjeId = e.target.value;
        let stanje = this.state.stanja.find((stanje) => stanje.id == stanjeId);

        let zadatak = this.state.zadatak;
        zadatak.stanje = stanje;

        this.setState({zadatak: zadatak});
    }

    async getSprintovi(){
        try{
            let result = await EvidencijaAxios.get("/sprintovi");
            let sprintovi = result.data;
            this.setState({sprintovi: sprintovi});
            console.log("test1");
        }catch(error){
            console.log(error);
            alert("Greska pri pribavljanju sprintova");
        }
    }

    async getStanja(){
        try{
            let result = await EvidencijaAxios.get("/stanja");
            let stanja = result.data;
            this.setState({stanja: stanja});
            console.log("test1");
        }catch(error){
            console.log(error);
            alert("Greska pri pribavljanju stanja");
        }
    }



    async create(e){
        e.preventDefault();

        try{
            let zadatak = this.state.zadatak;
            let zadatakDTO = {
                ime: zadatak.ime,
                zaduzeni: zadatak.zaduzeni,
                bodovi: zadatak.bodovi,
                sprintDTO: zadatak.sprint,
                stanjeDTO: zadatak.stanje
            }

            let response = await EvidencijaAxios.post("/zadaci", zadatakDTO);
         this.props.history.push("/zadaci");
    }catch(error){
        alert("Grska prilkom snimanja zadatka");
    }
    }

    sprintSelectionChanged(e){

        let sprintId = e.target.value;
        let sprint = this.state.sprintovi.find((sprint) => sprint.id == sprintId);

        let zadatak = this.state.zadatak;
        zadatak.sprint = sprint;

        this.setState({zadatak: zadatak});
    }
    render(){
        return (
            <div>
                <h1>Dodavanje zadataka</h1>
                <form>
                <label htmlFor="pIme">Ime zadatka</label>
                <input id="pZaduzeni" name="ime" onChange={(e)=>this.valueInputChanged(e)}/> <br/>
                <label htmlFor="pIme">Zaduzeni</label>
                <input id="pZaduzeni" name="zaduzeni" onChange={(e)=>this.valueInputChanged(e)}/> <br/>
                <label htmlFor="pBodovi">Bodovi</label>
                <input id="pBodovi" name="bodovi" onChange={(e)=>this.valueInputChanged(e)}/> <br/>
                <label htmlFor="pSprint">Sprintovi</label>
                    
                    <select id="pSprint" onChange={event => this.sprintSelectionChanged(event)}>
                        <option></option>
                        {
                            this.state.sprintovi.map((sprint) => {
                                return (
                                    <option key={sprint.id} value={sprint.id}>{sprint.ime}</option>
                                )
                            })
                        }
                    </select><br/>
                    <label htmlFor="pStanje">Stanja</label>
                    
                    <select id="pStanje" onChange={event => this.stanjeSelectionChanged(event)}>
                        <option></option>
                        {
                            this.state.stanja.map((stanje) => {
                                return (
                                    <option key={stanje.id} value={stanje.id}>{stanje.ime}</option>
                                )
                            })
                        }
                    </select><br/>
                    <button onClick={(event)=>{this.create(event);}}>Dodaj</button>
                </form>
            </div>
        )
    }
}

export default AddZadaci;