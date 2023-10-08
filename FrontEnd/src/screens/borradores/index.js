import { useState } from "react";
import Papa from "papaparse";

export default function Borradores() {
  const [file, setFile] = useState(null);
  console.log(file);

  const handleFileUpload = (e) => {
    const files = e.target.files;
    if (files) {
      Papa.parse(files[0], {
        complete: (results) => {
          setFile(results.data);
        },
      });
    }
  };
  return (
    <div>
      <div className='p-11 w-1/2 m-5 bg-blue-400 text-white rounded-lg'>
        <input
          type='file'
          accept='.csv,.xlsx,.xls'
          onChange={(e) => {
            handleFileUpload(e);
          }}
        />
      </div>
    </div>
  );
}
