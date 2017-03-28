using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class moverclick : MonoBehaviour {

    private Vector3 _target;
    public bool _hasArrive;
    public float _speed;
    public float _distanceToArrive;
    public GameObject particle;
    RaycastHit hit;
    // Use this for initialization
    void Start()
    {
        begin();
    }

    // Update is called once per frame
    void Update(){
    
        if (Input.GetMouseButtonDown(0)){
            Ray ray = Camera.main.ScreenPointToRay(Input.mousePosition);
            if (Physics.Raycast(ray, out hit))
            {
                _target = hit.point;

            }
            Debug.Log(_target);
            move();                  
        }
       
        
    }
    
    private void Arrive()
    {
         begin();
    }

   

    public bool HasArrive()
    {
        if ((transform.position - _target).magnitude <= _distanceToArrive)
            return true;
        return false;
    }

    private void begin()
    {
        
        _hasArrive = false;

    }

    public void move()
    {
        while (!_hasArrive) { 
            Vector3 direction = _target - this.transform.position;
            Vector3 step = direction.normalized * _speed * Time.deltaTime;
            this.transform.position += step;
            _hasArrive = HasArrive();
            
        }begin();

    }
}
