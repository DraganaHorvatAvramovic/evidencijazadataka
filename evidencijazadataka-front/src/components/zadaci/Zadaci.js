import React from 'react';
import EvidencijaAxios from './../../apis/EvidencijaAxios';
import {Button, Form, Table} from "react-bootstrap";

class Zadaci extends React.Component {
    constructor(props){
        super(props);

        this.state = {
            zadaci: [],
            sprintovi: [],
            search: {ime: "", sprintId: -1},
            pageNo: 0,
            totalPages: 0
        }
    }

    componentDidMount(){
        this.getZadaci(0);
        this.getSprintovi();
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



    getZadaci(page){
        let config = {
            params: {
                pageNo: this.state.pageNo
            },
        }
        if(this.state.search.ime != ""){
            config.params.ime = this.state.search.ime;
        }
        if(this.state.search.sprintId != -1){
            config.params.sprintId = this.state.search.sprintId;
        }
        EvidencijaAxios.get('/zadaci', config)
        .then(res => {
            // handle success
            console.log(res);
            this.setState({
                
                zadaci: res.data, 
                totalPages: res.headers["total-pages"]
             });
       })
       .catch(error => {
           // handle error
           console.log(error);
           alert('Error occured please try again!');
       });

    }

    goToAdd(){
        this.props.history.push('/zadaci/add'); 
    }


    doSearch(){
        this.getZadaci(0);
    }
    deleteFromState(zadatakId) {
        var zadaci = this.state.zadaci;
        zadaci.forEach((element, index) => {
            if (element.id === zadatakId) {
                zadaci.splice(index, 1);
                this.setState({zadaci: zadaci});
            }
        });
    }

    delete(zadatakId) {
        EvidencijaAxios.delete('/zadaci/' + zadatakId)
        .then(res => {
            // handle success
            console.log(res);
            alert('Zadatak je uspesno obrisan!');
            this.deleteFromState(zadatakId); // ili refresh page-a window.location.reload();
        })
        .catch(error => {
            // handle error
            console.log(error);
            alert('Error occured please try again!');
         });
    }

    goToEdit(zadatakId){
        this.props.history.push('/zadaci/edit/'+ zadatakId); 
    }

    async promenaStanja(id){
        try {
            await EvidencijaAxios.put('/zadaci/' +id +'/promenastanja', {});
          this.getZadaci(0);
        } catch (error){
            alert("Nije moguce promeniti stanje!")
        }
    }

    renderZadaci(){
        return this.state.zadaci.map((zadatak) =>{
            return (
                <tr key={zadatak.id}>
                    <td>{zadatak.ime}</td>
                    <td>{zadatak.zaduzeni}</td>
                    <td>{zadatak.bodovi}</td>
                    <td>{zadatak.sprintDTO.ime}</td>
                    <td>{zadatak.stanjeDTO.ime}</td>
                    <td><Button  disabled={zadatak.stanjeDTO.id === 1} variant="info" onClick={() => this.promenaStanja(zadatak.id)}>Promena stanja</Button></td>
                    <td><Button variant="warning" onClick={() => this.goToEdit(zadatak.id)}>Edit</Button></td>
                  <td><Button variant="danger" onClick={() => this.delete(zadatak.id)}>Delete</Button></td>
                </tr>
            )
        })
    }

    searchValueInputChange(event) {
        let control = event.target;
    
        let name = control.name;
        let value = control.value;
    
        let search = this.state.search;
        search[name] = value;
    
        this.setState({ search: search });
    }

    render(){
        return (
            <div>
                <h1>Zadaci</h1>
                <div>
                <Button onClick={() => this.goToAdd() }>Dodaj</Button> 
                <br/>
                <Form style={{marginTop:35}}>
                    <Form.Group>
                        <Form.Label>Ime zadatka</Form.Label>
                        <Form.Control name="ime" as="input" value={this.state.search.ime} onChange={(e) => this.searchValueInputChange(e)}></Form.Control>
                    </Form.Group>
                    <Form.Group>
                        <Form.Label>Sprint</Form.Label>
                        <Form.Control name="sprintId" as="select" value={this.state.search.prevoznikId}  onChange={(e) => this.searchValueInputChange(e)}>
                        <option value={-1}></option>
                                        {this.state.sprintovi.map((sprint) => {
                                     return (
                                    <option value={sprint.id} key={sprint.id}>
                                        {sprint.ime}
                                     </option>
                                 );
                                 })}
                        </Form.Control>
                    </Form.Group>
                    <Button onClick={() => this.doSearch()}>Pretraga</Button>
                </Form>
                <Table style={{marginTop:5}}>
                    <thead>
                        <tr>
                            <th>Ime zadatka</th>
                            <th>Zaduzeni</th>
                            <th>Bodovi</th>
                            <th></th>
                        </tr>
                    </thead>
                    <tbody>
                        {this.renderZadaci()}
                    </tbody>
                </Table>
                <div><Button disabled={this.state.pageNo==0} onClick={() => this.getZadaci(this.state.pageNo= this.state.pageNo-1)} >Previous</Button>
                    <Button disabled={this.state.pageNo==this.state.totalPages-1} onClick={() =>  this.getZadaci(this.state.pageNo= this.state.pageNo+1)}>Next</Button></div>                 
                </div>
            </div>
        )
    }
}

export default Zadaci;