import React from 'react';
import EvidencijaAxios from './../../apis/EvidencijaAxios';

class EditZadatka extends React.Component {
    constructor(props){
        super(props);

        this.state = {
            zadatakId: -1,
            ime: "",
            zaduzeni: "",
            bodovi: 0
        }
    }

    componentDidMount(){
        this.getZadaciById(this.props.match.params.id);
    }

    getZadaciById(zadatakId){
        EvidencijaAxios.get('/zadaci/' + zadatakId)
        .then(res => {
            // handle success
            console.log(res);
            this.setState({zadatakId: res.data.id, ime: res.data.ime, zaduzeni: res.data.zaduzeni, bodovi: res.data.bodovi});
        })
        .catch(error => {
            // handle error
            console.log(error);
            alert('Greska prilkom ucitavanja!');
         });  
    }

    valueImeChanged = event =>{
        console.log(event.target.value);

        const { name, value } = event.target;
        console.log(name + ", " + value);

        this.setState((state, props) => ({
            ime: value
        }));;
    }

    valueZaduzeniChanged = event =>{
        console.log(event.target.value);

        const { name, value } = event.target;
        console.log(name + ", " + value);

        this.setState((state, props) => ({
            zaduzeni: value
        }));;
    }

    valueBodoviChanged = event =>{
        console.log(event.target.value);

        const { name, value } = event.target;
        console.log(name + ", " + value);

        this.setState((state, props) => ({
            bodovi: value
        }));;
    }
    edit(){
        var params = {
            'id': this.state.zadatakId,
            'ime': this.state.ime,
            'zaduzeni': this.state.zaduzeni,
            'bodovi': this.state.bodovi
           
        };

        EvidencijaAxios.put('/zadaci/' + this.state.zadatakId, params)
        .then(res => {
            // handle success
            console.log(res);
            alert('Zadatak je uspesno izmenjen!');
            this.props.history.push('/zadaci');
        })
        .catch(error => {
            // handle error
            console.log(error);
            alert('Greska prilkom izmene!');
         });

    }


    render(){
        return(
            <div>
                <form>
                <label htmlFor="pIme">Ime zadatka</label>
                <input id="pIme" type="text" value={this.state.ime} onChange={(e)=>this.valueImeChanged(e)}/> <br/>
                <label htmlFor="pZaduzeni">Ime zadatka</label>
                <input id="pZaduzeni" type="text" value={this.state.zaduzeni} onChange={(e)=>this.valueZaduzeniChanged(e)}/> <br/>
                <label htmlFor="pBodovi">Ime zadatka</label>
                <input id="pBodovi" type="number" value={this.state.bodovi} onChange={(e)=>this.valueBodoviChanged(e)}/> <br/>
                <button className="button button-navy" onClick={() => this.edit()}>Edit</button>
                </form>
            </div>
        )
    }
}

export default EditZadatka;